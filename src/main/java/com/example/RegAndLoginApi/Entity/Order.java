package com.example.RegAndLoginApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_and_books",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id",columnDefinition = "BIGINT DEFAULT 0"))
    private List<Book> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User userDetails;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name="customer_address")
    private String customerAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "order_date")
    private LocalDateTime orderTime;

    @Column(name = "order_status")
    private String orderStatus;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderTime=" + orderTime +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
