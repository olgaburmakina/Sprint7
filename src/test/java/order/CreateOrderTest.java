package order;

import io.restassured.response.Response;
import org.example.order.Order;
import org.example.order.OrderChecks;
import org.example.order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)

public class CreateOrderTest {
    private final OrderClient data = new OrderClient();
    private final OrderChecks checks = new OrderChecks();
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] colorOfScooter(){
        return new Object[][]{
                { "Наруто", "Узумаки", "Коноха, 1", "1", "89991234567", 5, "2023-04-20", "Саске, я верну тебя в деревню.", Arrays.asList("BLACK")},
                { "Сакура", "Харуна", "Коноха, 15", "4", "88939564265", 4, "2023-05-11", "Орочимару, верни моего Саске.", Arrays.asList("GREY")},
                { "Сай", "Сай", "АНБУ, 150", "2", "89915267890", 2, "2023-09-01", "Кто такой этот Саске?", Arrays.asList("BLACK", "GREY")},
                { "Ямато", "Капитан", "Коноха АНБУ, 69", "1", "89993215476", 3, "2023-03-30", "Какаши, вернись.", Arrays.asList("")},
        };
    }

    @Test
    public void orderCreation(){
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = data.create(order);
        checks.orderCreatedSuccessfully(response);
    }
}
