package com.example.RegAndLoginApi.Service;

import com.example.RegAndLoginApi.Entity.User;
import com.example.RegAndLoginApi.Repository.UserRepository;
import com.example.RegAndLoginApi.Requests.OrderRequest;
import com.example.RegAndLoginApi.Entity.Book;
import com.example.RegAndLoginApi.Entity.Order;
import com.example.RegAndLoginApi.Repository.BookRepository;
import com.example.RegAndLoginApi.Repository.OrderRepository;
import com.example.RegAndLoginApi.Response.BookResponse;
import com.example.RegAndLoginApi.Response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderResponse saveOrderDetails(OrderRequest orderRequest) {
//        Book book1= bookRepository.findById(order.getBook()).orElse(null);

//        book1.setInventoryCount(book1.getInventoryCount()-1);
//        var order1=Order.builder()
//                .book(book1)
//                .customerName(order.getCustomerName())
//                .customerEmail(order.getCustomerEmail())
//                .phoneNumber(order.getPhoneNumber())
//                .orderTime(LocalDateTime.now())
//                .orderStatus("Order Placed")
//                .build();
//        orderRepository.save(order1);
//        return order1;
        List<Book> books = new ArrayList<>();
        List<Long> orderedBooks = orderRequest.getBookIds();
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Long id : orderedBooks) {
            Book book1 = bookRepository.findById(id).orElse(null);
            if (book1 != null && book1.getInventoryCount() >= 1) {
                System.out.println(book1.getInventoryCount());
                book1.setInventoryCount(book1.getInventoryCount() - 1);
                System.out.println(book1.getInventoryCount());
                bookRepository.save(book1);
                books.add(book1);
                System.out.println(books);
                BookResponse bookResponse = BookResponse.builder()
                        .id(book1.getId())
                        .title(book1.getTitle())
                        .pages(book1.getPages())
                        .inventoryCount(book1.getInventoryCount())
                        .author(book1.getAuthor())
                        .price(book1.getPrice())
                        .build();
                bookResponseList.add(bookResponse);
            } else {
                throw new IllegalArgumentException("Book is not available");
            }
        }
        User user = userRepository.findByEmail(orderRequest.getCustomerEmail());
        System.out.println(user);
        System.out.println(books);
        Order order1 = Order.builder()
                .books(books)
                .customerName(orderRequest.getCustomerName())
                .customerEmail(orderRequest.getCustomerEmail())
                .customerAddress(orderRequest.getCustomerAddress())
                .phoneNumber(orderRequest.getPhoneNumber())
                .orderTime(LocalDateTime.now())
                .orderStatus("Order Placed")
                .userDetails(user)
                .build();
        orderRepository.save(order1);

        OrderResponse orderResponse = OrderResponse.builder()
                .books(bookResponseList)
                .customerName(orderRequest.getCustomerName())
                .customerEmail(orderRequest.getCustomerEmail())
                .phoneNumber(orderRequest.getPhoneNumber())
                .customerAddress(orderRequest.getCustomerAddress())
                .orderTime(LocalDateTime.now())
                .orderStatus("Order Placed")
                .build();
        return orderResponse;
    }


    @Override
    public OrderResponse updateOrderStatus(Long id, String orderStatus) {
        Order order = orderRepository.findById(id).orElse(null);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        OrderResponse orderResponse = OrderResponse.builder().customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .phoneNumber(order.getPhoneNumber())
                .customerAddress(order.getCustomerAddress())
                .orderTime(LocalDateTime.now())
                .orderStatus(orderStatus).build();
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .id(order.getId())
                    .customerName(order.getCustomerName())
                    .customerEmail(order.getCustomerEmail())
                    .customerAddress(order.getCustomerAddress())
                    .phoneNumber(order.getPhoneNumber())
                    .orderTime(order.getOrderTime())
                    .orderStatus(order.getOrderStatus())
                    .build();
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .customerAddress(order.getCustomerAddress())
                .phoneNumber(order.getPhoneNumber())
                .orderTime(order.getOrderTime())
                .orderStatus(order.getOrderStatus())
                .build();
        return orderResponse;
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderResponse> getAllOrdersByUser(String email) {
        List<Order> orders = orderRepository.findByUserDetailsEmail(email);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            List<BookResponse> bookResponseList = new ArrayList<>();
            List<Book> books = order.getBooks();
            for (Book book1 : books) {
                BookResponse bookResponse = BookResponse.builder()
                        .id(book1.getId())
                        .title(book1.getTitle())
                        .pages(book1.getPages())
                        .inventoryCount(book1.getInventoryCount())
                        .author(book1.getAuthor())
                        .price(book1.getPrice())
                        .build();
                bookResponseList.add(bookResponse);
            }
            OrderResponse orderResponse = OrderResponse.builder()
                    .books(bookResponseList)
                    .id(order.getId())
                    .customerName(order.getCustomerName())
                    .customerEmail(order.getCustomerEmail())
                    .customerAddress(order.getCustomerAddress())
                    .phoneNumber(order.getPhoneNumber())
                    .orderTime(order.getOrderTime())
                    .orderStatus(order.getOrderStatus())
                    .build();
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}
