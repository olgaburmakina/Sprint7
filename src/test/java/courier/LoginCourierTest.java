package courier;

import io.restassured.response.Response;
import org.example.courier.Courier;
import org.example.courier.CourierChecks;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierChecks checks = new CourierChecks();
    private int courierId;

    @Test
    public void loginCourier(){
        Courier courier = generator.loginData();
        Response response = client.login(courier);
        checks.loginSuccessfully(response);
    }

    @Test
    public void wrongLoginCourier() {
        Courier courier = generator.loginData();
        courier.setLogin("Kakasi");
        Response response = client.login(courier);
        checks.loginFailed(response);
    }

    @Test
    public void wrongPasswordCourier() {
        Courier courier = generator.loginData();
        courier.setPassword("1a2b3c4d");
        Response response = client.login(courier);
        checks.loginFailed(response);
    }

    @Test
    public void loginWithoutPassword(){
        Courier courier = generator.loginData();
        courier.setPassword("");
        Response response = client.login(courier);
        checks.loginWithoutPasswordFailed(response);
    }

    @After
    public void deleteCourier(){
        if (courierId > 0){
            Response responseDelete = client.deleteCourier(courierId);
            checks.deleteSuccessfully(responseDelete);
        }
    }
}
