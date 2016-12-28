package fr.him.bdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.logging.Logger;

import org.assertj.core.api.Condition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
	/**
	 * LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(StepDefinitions.class.getName());
	/**
	 * User repository.
	 */
	private UserRepository userRepository = new UserRepository();
	
	//Scenario: Insertion
	
	private Integer userCount;
	private Integer insertedId;
	
	@Given("^Repository contains N users$")
	public void repository_contains_N_users() throws Throwable {
	    userCount = userRepository.count();
	    assertThat(userCount).isGreaterThanOrEqualTo(0);
	}

	@When("^I created one user$")
	public void i_created_one_user() throws Throwable {
	    User u = new User("Tom", 50, "tom@tom.fr");
	    insertedId = userRepository.create(u);
	}

	@Then("^I get the ID of the user and repository contains more than N users$")
	public void i_get_the_ID_of_the_user_and_repository_contains_more_than_N_users() throws Throwable {
		LOGGER.info("Inserted id = " + insertedId);
	    assertThat(insertedId).isNotNull();
	    assertThat(userRepository.count()).isGreaterThan(userCount);
	}
	
	//Scenario: Read
	
	private User readUser;
	
	@When("^I extract the user of ID (\\d+)$")
	public void i_extract_the_user_of_ID(int arg1) throws Throwable {
	    readUser = userRepository.read(arg1);
	}
	
	@Then("^I get the user of ID (\\d+) containing the data (.*), (.*), (.*)$")
	public void i_get_the_user_of_ID_containing_the_data(int arg1, String name, int age, String email) throws Throwable {
	    assertThat(readUser).isNotNull();
	    assertThat(readUser.getId()).isEqualTo(arg1);
	    assertThat(readUser.getName()).isEqualTo(name);
	    assertThat(readUser.getAge()).isEqualTo(age);
	    assertThat(readUser.getEmail()).isEqualTo(email);
	}
	
	//Scenario: Update
	
	@Given("^Repository contains the following users$")
	public void repository_contains_the_following_users(DataTable arg1) throws Throwable {
	    List<User> actualUsers = userRepository.readAll();
	    arg1.asList(User.class).stream().forEach(e -> {
	    	assertThat(actualUsers).haveExactly(1, new Condition<User>() {
				@Override
				public boolean matches(User user) {
					return user.getId().equals(e.getId())
							&& user.getName().equals(e.getName())
							&& user.getAge().equals(e.getAge())
							&& user.getEmail().equals(e.getEmail());
				}
			});
	    });
	}
	
	@When("^I update the age and email of user of id (\\d+) by data (\\d+), (.*)$")
	public void i_update_the_age_and_email_of_user_of_id_by_data(int arg1, int age, String email) throws Throwable {
	    User user = userRepository.read(arg1);
	    user.setAge(age);
	    user.setEmail(email);
	    readUser = user;
	}
	
	//Scenario: Delete
	
	@When("^I delete the user of ID (\\d+)$")
	public void i_delete_the_user_of_ID(int arg1) throws Throwable {
	    userRepository.delete(arg1);
	}

	@Then("^Repository contains less than N users$")
	public void repository_contains_less_than_N_users() throws Throwable {
	   assertThat(userRepository.count()).isLessThan(userCount);
	}

}
