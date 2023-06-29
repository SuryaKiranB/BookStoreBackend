package com.example.RegAndLoginApi.Requests;

import com.example.RegAndLoginApi.Entity.Book;
import com.example.RegAndLoginApi.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<Long> bookIds;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String phoneNumber;

//    @Override
//    public String toString() {
//        return "OrderRequest{" +
//                "books=" + books +
//                ", customerName='" + customerName + '\'' +
//                ", customerEmail='" + customerEmail + '\'' +
//                ", customerAddress='" + customerAddress + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                '}';
//    }
}

