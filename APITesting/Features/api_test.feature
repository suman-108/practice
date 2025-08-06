Feature: API Testing with Rest Assured and cucumber

  Scenario: Get user details
    Given the API endpoint is "generator.swagger.io/api"
    When I send a GET request to "/users/2"
    Then the response status code should be 200
    And the user first name should be "Janet"
    
    
  