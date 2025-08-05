package stepDefinition;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pages.UserAPI;

public class UserApiSteps {
	private Response response;
    private String baseUri;
    
    UserAPI userAPI = new UserAPI();

    //Without Page Object Model
    @Given("the API endpoint is {string}")
    public void the_api_endpoint_is(String uri) {
        baseUri = uri;
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String path) {
        response = RestAssured.get(baseUri + path);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the user first name should be {string}")
    public void the_user_first_name_should_be(String firstName) {
        String actualFirstName = response.jsonPath().getString("data.first_name");
        assertEquals(firstName, actualFirstName);
    }
    
   
    @Given("I have the user endpoint")
    public void i_have_the_user_endpoint() {
        // Configured in UserAPI
    }

    @When("I fetch user with ID {int}")
    public void i_fetch_user_with_id(Integer id) {
        userAPI.getUserById(id);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedCode) {
        assertEquals(expectedCode.intValue(), userAPI.getStatusCode());
    }

    @Then("the username should be {string}")
    public void the_username_should_be(String expectedName) {
        assertEquals(expectedName, userAPI.getUserName());
    }
}
