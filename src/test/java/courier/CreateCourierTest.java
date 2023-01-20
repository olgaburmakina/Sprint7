package courier;

import io.restassured.response.Response;
import org.example.courier.Courier;
import org.example.courier.CourierChecks;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.junit.Test;

public class CreateCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks checks = new CourierChecks();
    private final CourierGenerator generator = new CourierGenerator();
    private int courierId;

    @Test
    public void createCourier() {
        Courier courier = generator.random();
        Response response = client.createCourier(courier);
        checks.createdSuccessfully(response);
        courier.setFirstName(null);
        Response responseId = client.login(courier);
        responseId.then().log().all();
        courierId = responseId.path("id");
    }

    @Test
    public void createCourierTwice(){
        Courier courier = generator.random();
        client.createCourier(courier);
        Response response = client.createCourier(courier);
        checks.creationFailed(response);
    }

    @Test
    public void createWithoutPassword() {
        Courier courier = generator.generic();
        courier.setPassword(null);
        Response response = client.createCourier(courier);
        checks.creationWithoutPasswordFailed(response);
    }

}