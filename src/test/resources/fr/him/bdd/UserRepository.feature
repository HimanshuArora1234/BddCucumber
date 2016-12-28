Feature: User Create, read, update and delete (CRUD) features

    Scenario: Create
      Given Repository contains N users
      When I created one user
      Then I get the ID of the user and repository contains more than N users
      
    Scenario Outline: Read
      Given Repository contains N users
      When I extract the user of ID <id>
      Then I get the user of ID <id> containing the data <name>, <age>, <email>
 
      Examples:
        | id | name   | age         | email 	        |
        | 1  | James  | 55   		| j@startrek.com    |
        | 2  | Luke   | 60			| lu@starwars.com   |
        | 3  | Leia   | 70			| le@starwars.com   |
        
    Scenario Outline: Update
    	Given Repository contains the following users
      		| id | name   | age         | email 	        |
      		| 3  | Leia   | 70			| le@starwars.com   |
    	When I update the age and email of user of id <id> by data <age>, <email>
    	Then I get the user of ID <id> containing the data <name>, <age>, <email>
 
    	Examples:
      		| id | name   | age         | email 	          |
      		| 3  | Leia   | 20			| leia@starwars.com   |
      		
    Scenario Outline: Delete
      Given Repository contains N users
      When I extract the user of ID <id>
      	And I delete the user of ID <id>
      Then Repository contains less than N users
      	
      Examples:
        | id | name   | age         | email 	        |
        | 1  | James  | 55   		| j@startrek.com    |
        | 2  | Luke   | 60			| lu@starwars.com   |		 