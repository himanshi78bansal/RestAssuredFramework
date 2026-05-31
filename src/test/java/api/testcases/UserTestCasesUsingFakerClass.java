package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserTestCasesUsingFakerClass {
    Faker faker;
    UserPayload user;
    public static Logger logger;


    @BeforeClass
    public void generateTestData(){
        faker = new Faker();
        user = new UserPayload();
        user.setId(faker.hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());

        // obtain logger
        logger = LogManager.getLogger("RestAssuredFramework");
    }

    @Test(priority = 1)
    public void testCreateUser(){
        Response response = UserEndPoints.createUser(user);
        System.out.println("Create User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("Create user accounts");
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response response = UserEndPoints.getUser(this.user.getUsername());
        System.out.println("Get User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("Get all users");
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        user.setFirstName(faker.name().firstName());
        Response response = UserEndPoints.updateUser(user, this.user.getUsername());
        System.out.println("Update User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("Update user");
    }

    @Test(priority = 4)
    public void testDelUser(){
        Response response = UserEndPoints.delUser(this.user.getUsername());
        System.out.println("Delete User");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("Delete user");
    }
}
