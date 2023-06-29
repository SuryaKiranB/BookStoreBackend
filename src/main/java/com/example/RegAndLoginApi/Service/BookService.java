package com.example.RegAndLoginApi.Service;

import com.example.RegAndLoginApi.Entity.Book;
import com.example.RegAndLoginApi.Entity.Order;
import com.example.RegAndLoginApi.Repository.BookRepository;
import com.example.RegAndLoginApi.Repository.OrderRepository;
import com.example.RegAndLoginApi.Response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class BookService implements IBookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book book : books) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(book.getId());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setAuthor(book.getAuthor());
            bookResponse.setPages(book.getPages());
            bookResponse.setPrice(book.getPrice());
            bookResponse.setInventoryCount(book.getInventoryCount());
            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }
    @Override
    public BookResponse getBookById(Long id) {
        Book book= bookRepository.findById(id).orElse(null);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setPages(book.getPages());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setInventoryCount(book.getInventoryCount());
        return bookResponse;
    }

//    @Override
//    public String createBook(Book book) {
//        bookRepository.save(book);
//        return "Saved.";
//    }
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public BookResponse updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setInventoryCount(updatedBook.getInventoryCount());
            bookRepository.save(existingBook);
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(existingBook.getId());
            bookResponse.setTitle(existingBook.getTitle());
            bookResponse.setAuthor(existingBook.getAuthor());
            bookResponse.setPages(existingBook.getPages());
            bookResponse.setPrice(existingBook.getPrice());
            bookResponse.setInventoryCount(existingBook.getInventoryCount());
            return bookResponse;
        } else {
            return null;
        }
    }
    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}

