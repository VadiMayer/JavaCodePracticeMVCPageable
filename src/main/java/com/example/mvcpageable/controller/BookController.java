package com.example.mvcpageable.controller;

import com.example.mvcpageable.model.Book;
import com.example.mvcpageable.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping()
    public ResponseEntity<Page<Book>> getBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getBooks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) throws URISyntaxException {
        return ResponseEntity.created(new URI("/books/" + book.getId())).body(bookService.updateOrCreateBook(book));
    }

    @PutMapping()
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok().body(bookService.updateOrCreateBook(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (bookService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
