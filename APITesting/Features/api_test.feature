Feature: API Testing with Rest Assured and cucumber

  Scenario: Get user details
    Given the API endpoint is "generator.swagger.io/api"
    When I send a GET request to "/users/2"
    Then the response status code should be 200
    And the user first name should be "Janet"
    
    
  Scenario: Get a user by ID
    Given I have the user endpoint
    When I fetch user with ID 1
    Then the response status code should be 200
    And the username should be "Leanne Graham"  