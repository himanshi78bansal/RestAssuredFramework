package api.testcases;

import api.endpoints.StoreEndPoints;
import api.payload.StorePayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.codehaus.groovy.transform.trait.Traits;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;

public class StoreTestCases {

    Faker faker;
    StorePayload store;

    @BeforeClass
    public void generateTestData(){
        faker = new Faker();
        store = new StorePayload();
        store.setId((int) faker.number().randomNumber());
        store.setPetId((int) faker.number().randomNumber());
        store.setComplete(true);
        store.setQuantity(faker.number().numberBetween(5, 10));
        store.setShipDate(OffsetDateTime.now());
        store.setStatus("placed");
    }

    @Test
    public void testStoreCreateOrder(StorePayload payload){
        Response response = StoreEndPoints.storeCreateOrder(store);
        System.out.println("Store Create Order");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testStoreGetOrder(){
        Response response = StoreEndPoints.storeGetOrder(this.store.getId());
        System.out.println("Get Store Order");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void testStoreDelOrder(){
        Response response = StoreEndPoints.storeDelOrder(this.store.getId());
        System.out.println("Delete Store Order");
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }
}
