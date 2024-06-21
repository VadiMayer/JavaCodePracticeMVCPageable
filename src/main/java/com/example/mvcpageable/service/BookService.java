package com.example.mvcpageable.service;

import com.example.mvcpageable.model.Book;
import com.example.mvcpageable.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book getBook(long id) {
        return bookRepository.getBook(id);
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.getBooks(pageable);
    }

    public Book updateOrCreateBook(Book book) {
        Book bookFromBase = bookRepository.getBook(book.getId());
        if (bookFromBase == null) {
            return bookRepository.create(book);
        } else {
            bookFromBase.setName(book.getName());
            bookFromBase.setTitle(book.getTitle());
            return bookRepository.create(bookFromBase);
        }
    }

    public boolean delete(long id) {
        Book book = bookRepository.getBook(id);
        if (book == null) {
            return false;
        } else {
            bookRepository.delete(id);
            return true;
        }
    }
}
