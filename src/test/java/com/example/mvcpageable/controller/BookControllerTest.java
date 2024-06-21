package com.example.mvcpageable.controller;

import com.example.mvcpageable.model.Book;
import com.example.mvcpageable.repository.BookRepository;
import com.example.mvcpageable.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookControllerTest.class)
class BookControllerTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<Book> listBooks;

    @BeforeEach
    void setUp() {
        listBooks = Arrays.asList(
                new Book(1L, "Book One", "Author One"),
                new Book(2L, "Book Two", "Author Two"),
                new Book(3L, "Book Three", "Author Three")
        );
    }

    @Test
    void getBooksDefaultPageable() throws Exception {
        Page<Book> bookPage = new PageImpl<>(listBooks, PageRequest.of(0,10), listBooks.size());
        when(bookService.getBooks(PageRequest.of(0, 10))).thenReturn(bookPage);
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(listBooks.size()));
    }

}