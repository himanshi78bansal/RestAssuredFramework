package api.endpoints;

import api.payload.StorePayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class StoreEndPoints {
    // load routes(URLs) from routes.properties via ResourceBundle javaUtility class
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("Routes");
        return routes;
    }

    public static Response storeCreateOrder(StorePayload payload){
        String storePostURL = getURL().getString("storePostURL");
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)
                .when().post(storePostURL);

        return response;
    }

    public static Response storeGetOrder(int OrderID){
        String storeGetURL = getURL().getString("storeGetURL");
        Response response = given().accept(ContentType.JSON).pathParam("orderId", OrderID)
                .when().get(storeGetURL);

        return response;
    }

    public static Response storeDelOrder(int OrderID){
        String storeDelURL = getURL().getString("storeDelURL");
        Response response = given().accept(ContentType.JSON).pathParam("OrderId", OrderID)
                .when().delete(storeDelURL);

        return response;
    }
}
