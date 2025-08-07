package stepDefinition;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExcelUtil;
import utils.ExcelUtil;

public class UserApiSteps {
	private Response response;
    private String baseUri;

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
    
    @Then("I log the response status code to Excel at row {int}, column {int}")
    public void logStatusToExcel(int rowNum, int colNum) {
        int statusCode = response.getStatusCode();
        String status = (statusCode == 200) ? "200 - Pass" : statusCode + " - Fail";

        // Write to Excel
        ExcelUtil.writeStatusToExcel("testdata.xlsx", "Sheet1", rowNum, colNum, status);
    }
}
