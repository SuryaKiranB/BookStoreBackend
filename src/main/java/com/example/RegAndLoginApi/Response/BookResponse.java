package com.example.RegAndLoginApi.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private int pages;
    private int price;
    private int inventoryCount;

}
