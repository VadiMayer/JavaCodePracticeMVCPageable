package com.example.mvcpageable.repository;

import com.example.mvcpageable.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BookRepository {

    private CrudBookRepository crudBookRepository;

    public Book getBook(long id) {
        return crudBookRepository.findById(id).orElse(null);
    }

    public Page<Book> getBooks(Pageable pageable) {
        return crudBookRepository.findAll(pageable);
    }

    public Book create(Book book) {
        return crudBookRepository.save(book);
    }

    public void delete(long id) {
        crudBookRepository.deleteById(id);
    }

}
