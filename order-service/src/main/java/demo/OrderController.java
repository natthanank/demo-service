package demo;

import demo.adapter.User;
import demo.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserAdapter userAdapter;
    HashMap hashMap = new HashMap();

    @Autowired
    public OrderController(OrderRepository orderRepository, UserAdapter userAdapter) {
        this.orderRepository = orderRepository;
        this.userAdapter = userAdapter;
    }

    @GetMapping("/order/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable long userId) {

        List<Order> orderList = this.orderRepository.findByUserId(userId);
        Order order_1 = orderList.get(0);

        User user = this.userAdapter.getUserDetail(order_1.getUserId());
        for (Order order: orderList) {
            order.setUser( user );
        }

        return  orderList;
    }
}
