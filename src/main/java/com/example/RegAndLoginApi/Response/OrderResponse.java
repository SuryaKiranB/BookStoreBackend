package com.example.RegAndLoginApi.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private List<BookResponse> books;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String phoneNumber;
    private LocalDateTime orderTime;
    private String orderStatus;

//    @Override
//    public String toString() {
//        return "OrderRequest{" +
//                ", customerName='" + customerName + '\'' +
//                ", customerEmail='" + customerEmail + '\'' +
//                ", customerAddress='" + customerAddress + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", orderTime=" + orderTime +
//                ", orderStatus='" + orderStatus + '\'' +
//                '}';
//    }
}
