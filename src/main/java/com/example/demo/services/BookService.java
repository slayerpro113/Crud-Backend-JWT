package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.models.BookDTO;
import com.example.demo.repositories.BookRepository;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookDTO(Book book);

    BookDTO detailBookById(int bookId);

    String updateBook(BookDTO bookDTO);

    public Book getBook(BookDTO bookDTO);

    public List<BookDTO> getListBooks();

    public String addBook(BookDTO bookDTO);

    String deleteBook(int bookId);

}

