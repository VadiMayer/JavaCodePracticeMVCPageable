package com.example.mvcpageable.repository;

import com.example.mvcpageable.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CrudBookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
}
