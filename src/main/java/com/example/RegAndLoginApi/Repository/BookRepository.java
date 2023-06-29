package com.example.RegAndLoginApi.Repository;


import com.example.RegAndLoginApi.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);

    void deleteById(Long aLong);

    List<Book> findAll();
}
