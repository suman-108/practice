Feature: API Testing with Rest Assured

  Scenario: Get user details
    Given the API endpoint is "https://jsonplaceholder.typicode.com"
    When send a GET request to user Id "1"
    Then user first name should be "Leanne Graham"
    Then the response status code should be 200
    
    
  