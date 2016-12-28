package fr.him.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "json:target/cucumber/user.json",
                "html:target/cucumber/user.html",
                "pretty"
        },
        tags = {"~@ignored"})
public class RunBddTest {

}
