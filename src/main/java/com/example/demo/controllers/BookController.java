package com.example.demo.controllers;


import com.example.demo.models.BookDTO;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @CrossOrigin
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity getBookById(@PathVariable("id") int bookId) {
        BookDTO bookDTO = null;
        try {
            bookDTO = bookService.detailBookById(bookId);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity updateBook(@Validated @RequestBody BookDTO bookDTO) {
        String bookResult = bookService.updateBook(bookDTO);
        String jsonResult = "{\"message\": \"" + bookResult + "\"}";
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/newbook")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity addBook(@Validated @RequestBody BookDTO bookDTO) {
        String result = bookService.addBook(bookDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String bookDelete(@PathVariable(value = "id") int bookId) {
        return bookService.deleteBook(bookId);
    }

}

