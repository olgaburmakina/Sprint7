package order;

import io.restassured.response.Response;
import org.example.order.Order;
import org.example.order.OrderChecks;
import org.example.order.OrderClient;
import org.junit.Test;

public class OrderListInResponseTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks checks = new OrderChecks();

    @Test
    public void orderListInResponse(){
        Order order = new Order();
        Response response = client.get(order);
        checks.orderListNotNull(response);
    }
}
