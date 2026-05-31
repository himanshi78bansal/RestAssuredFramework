package api.endpoints;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    // load routes(URLs) from routes.properties via ResourceBundle javaUtility class
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(UserPayload payload){
        String userPostURL = getURL().getString("userPostURL");
        Response response  = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)
                              .when().post(userPostURL);

        return response;
    }

    public static Response getUser(String userName){
        String userGetURL = getURL().getString("userGetURL");
        Response response = given().accept(ContentType.JSON).pathParam("username", userName)
                              .when().get(userGetURL);

        return response;
    }

    public static Response updateUser (UserPayload payload, String userName){
        String userPutURL = getURL().getString("userPutURL");
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload).pathParam("username", userName)
                               .when().put(userPutURL);

        return response;
    }

    public static Response delUser (String userName){
        String userDelURL = getURL().getString("userDelURL");
        Response response = given().accept(ContentType.JSON).pathParam("username", userName)
                            .when().delete(userDelURL);

        return response;
    }
}
