package com.example.RegAndLoginApi.Controller;

import com.example.RegAndLoginApi.Requests.OrderRequest;
import com.example.RegAndLoginApi.Service.IOrderService;
import com.example.RegAndLoginApi.Response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order-store")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @PostMapping("/order-details")
    public OrderResponse orderDetails(@RequestBody OrderRequest orderRequest){
//        orderService.saveOrderDetails(orderRequest);
        return orderService.saveOrderDetails(orderRequest);
        //return order1;
    }

    @PutMapping("/update-delivery-status/{id}")
    public ResponseEntity<OrderResponse> updateDeliveryStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String orderStatus = requestBody.get("orderStatus");
        OrderResponse orderResponse = orderService.updateOrderStatus(id, orderStatus);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/get-order-by-id/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-orders-by-user/{email}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByUser(@PathVariable String email){
        List<OrderResponse> orders=orderService.getAllOrdersByUser(email);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
