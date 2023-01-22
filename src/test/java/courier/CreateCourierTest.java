package courier;

import io.restassured.response.Response;
import org.example.courier.Courier;
import org.example.courier.CourierChecks;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks checks = new CourierChecks();
    private final CourierGenerator generator = new CourierGenerator();
    private int courierId;

    @Test
    public void createCourier() {
        Courier courier = generator.random(); //создали рандомный логин
        Response response = client.createCourier(courier); //создали курьера
        checks.createdSuccessfully(response); //проверили ответ
        courier.setFirstName(null); //убрали ненужное для логина поле
        Response responseId = client.login(courier); //залогинились в созданный аккаунт
        responseId.then().log().all();
        courierId = responseId.path("id"); //вычислили курьера по айди
    }

    @Test
    public void createCourierTwice(){
        Courier courier = generator.random();
        client.createCourier(courier);
        Response response = client.createCourier(courier);
        checks.creationFailed(response);
    }
    @Test
    public void createWithoutLogin() {
        Courier courier = generator.generic();
        courier.setLogin(null);
        Response response = client.createCourier(courier);
        checks.creationWithoutLoginFailed(response);
    }

    @Test
    public void createWithoutPassword() {
        Courier courier = generator.generic();
        courier.setPassword(null);
        Response response = client.createCourier(courier);
        checks.creationWithoutPasswordFailed(response);
    }

    @After
    public void deleteCourier(){
        if (courierId > 0){
            Response responseDelete = client.deleteCourier(courierId);
            checks.deleteSuccessfully(responseDelete);
        }
    }
}