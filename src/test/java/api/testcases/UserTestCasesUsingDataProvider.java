package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestCasesUsingDataProvider {

    @Test(priority = 1, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String id, String userName, String firstName, String lastName, String email, String password, String phone){

        UserPayload user = new UserPayload();
        user.setId(Integer.parseInt(id));
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        Response response = UserEndPoints.createUser(user);
        System.out.println("Create User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserName", dataProviderClass = DataProviders.class)
    public void testGetUser(String userName){

        Response response = UserEndPoints.getUser(userName);
        System.out.println("Get User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

//    @Test(priority = 3, dataProvider = "UserName", dataProviderClass = DataProviders.class)
    @Test(priority = 3)
    public void testUpdateUser(){
        String userName = "user1";

        UserPayload user = new UserPayload();
        user.setFirstName("Anshika");

        Response response = UserEndPoints.updateUser(user, userName);
        System.out.println("Update User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);

        Response getResponse = UserEndPoints.getUser("user1");

        getResponse.then().log().all();
    }

//    @Test(priority = 4, dataProvider = "UserName", dataProviderClass = DataProviders.class)
    @Test(priority = 4)
    public void testDelUser(){
        String userName = "user2";

        Response response = UserEndPoints.delUser(userName);
        System.out.println("Delete User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }
}
