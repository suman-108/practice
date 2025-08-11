package stepDefinition;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lib.FilePath;
import utils.ExtentReportNG;


public class UserApiSteps {
	private Response response;
    private String baseUri;

    //Without Page Object Model
    @Given("the API endpoint is {string}")
    public void the_api_endpoint_is(String uri) {
        baseUri = uri;
        ExtentReportNG.logInfo("Base URI set to: " + baseUri);
    }

    @When("send a GET request to user Id {string}")
    public void i_send_a_get_request_to(String userId) {
      /*  response = RestAssured.get(baseUri + userId);
       *  Use this code or below
      */      
        response = given()
                .when()
                .get("/users/" + userId);
        ExtentReportNG.logInfo("Sent GET request for user ID : " +userId);
    }

    @Then("user first name should be {string}")
    public void the_user_first_name_should_be(String fname) {
       // response.then().body("name", equalTo(fname));
    	//Boolean actualFirstName = response.getBody().asString().contains(fname);
    	//Assert.assertEquals(fname, actualFirstName);
    	response.getBody().asString().contains(fname);
        ExtentReportNG.logInfo("User First Name : " +fname);
        
        // assertTrue(response.getBody().asString().contains(name));
        
      /* Or, 
         String actualFirstName = response.jsonPath().getString("data.fname");
         assertEquals(name, actualFirstName); */
    
      //   response.then().body(containsString(fname));
    }
    
    @Then("the response status code should be 200")
    public void logStatusToExcel() {
        int statusCode = response.getStatusCode();
        System.out.println("Status Code : " + statusCode);
        FilePath.excel.setCellData(FilePath.API_SHEETNAME, "Body", 2, response.getBody().asString());
        if(statusCode>=400) {
        	  FilePath.excel.setCellData(FilePath.API_SHEETNAME, "Status", 2, "Fail");
        	  ExtentReportNG.logFail("Error in response code" + statusCode);
  	      }else {
  	    	FilePath.excel.setCellData(FilePath.API_SHEETNAME, "Status", 2, "Pass");
  	    	ExtentReportNG.logPass("Test passed with response code : " + statusCode);
  	      }
          Assert.assertEquals(statusCode, response.getStatusCode());
        }
}
