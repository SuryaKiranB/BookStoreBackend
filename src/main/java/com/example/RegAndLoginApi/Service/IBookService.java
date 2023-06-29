package com.example.RegAndLoginApi.Service;

import com.example.RegAndLoginApi.Entity.Book;
import com.example.RegAndLoginApi.Entity.Order;
import com.example.RegAndLoginApi.Response.BookResponse;

import java.util.List;

public interface IBookService {
    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    Book createBook(Book book);

    BookResponse updateBook(Long id, Book book);

    boolean deleteBook(Long id);



//    String createBook(Book book);
}
