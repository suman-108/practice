package pages;

import static io.restassured.RestAssured.given;

import config.ConfigFile;
import io.restassured.response.Response;

public class UserAPI {
	private Response response;

    public void getUserById(int userId) {
        response = given()
                    .baseUri(ConfigFile.BASE_URL)
                    .when()
                    .get("/users/" + userId);
    }

    public Response getResponse() {
        return response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getUserName() {
        return response.jsonPath().getString("name");
    }

}
