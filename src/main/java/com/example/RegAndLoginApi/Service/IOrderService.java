package com.example.RegAndLoginApi.Service;

import com.example.RegAndLoginApi.Requests.OrderRequest;
import com.example.RegAndLoginApi.Response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse saveOrderDetails(OrderRequest orderRequest);

    OrderResponse updateOrderStatus(Long id, String orderStatus);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    boolean deleteOrder(Long id);

    List<OrderResponse> getAllOrdersByUser(String email);
}
