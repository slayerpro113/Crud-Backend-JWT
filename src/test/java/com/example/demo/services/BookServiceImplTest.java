package com.example.demo.services;

import com.example.demo.FlamingoApplication;
import com.example.demo.constants.ErrorMessageConstants;
import com.example.demo.constants.SuccessMessageContants;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.models.BookDTO;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlamingoApplication.class)
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*--------- AddBook(BookDTO)--------*/
    @Test
    public void whenAddBook_withValidBook_thenReturnSameBook() {
        BookDTO bookDTO = new BookDTO(1,"Harry Porter 22", "Harry Poster Book's Desc  22", "No Name11", "bookcover.png", "Comic", 1);
        String result = bookService.addBook(bookDTO);

        assertEquals(SuccessMessageContants.CREATE_SUCCESS, result);
    }

    @Test
    public void whenAddBook_withBookNameIsNull_thenReturnBookNameInvalid(){
        BookDTO bookDTO = new BookDTO(1,"", "Harry Poster Book's Desc", "No Name", "bookcover.png", "Novel", 1);
        String result = bookService.addBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_NAME_INVALID, result);
    }

    @Test
    public void whenAddBook_withBookAuthorIsNull_thenReturnBookAuthorInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "", "bookcover.png", "Novel", 1);
        String result = bookService.addBook(bookDTO);
        assertEquals(ErrorMessageConstants.BOOK_AUTHOR_INVALID, result);
    }

    @Test
    public void whenAddBook_withBookDescIsNull_thenReturnBookDescInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "", "No Name", "bookcover.png", "Novel", 1);
        String result = bookService.addBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_DESC_INVALID, result);
    }

    @Test
    public void whenAddBook_withBookCoverIsNull_thenReturnBookCoverInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "No Name", "", "Novel", 1);
        String result = bookService.addBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_COVER_INVALID, result);
    }

    @Test
    public void whenAddBook_withBookCoverIsNotFormat_thenReturnBookCoverIsNotFormat(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "No Name", "abcdef", "Novel", 1);
        String result = bookService.addBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_COVER_IS_NOT_FORMAT, result);
    }

    /*--------- Update Book(BookDTO)--------*/
    @Test
    public void whenUpdateBook_withValidBook_thenReturnSameBook() {
        BookDTO bookDTO = new BookDTO(96,"Harry Porter", "Harry Poster Book's Desc", "No Name", "bookcover.png", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(SuccessMessageContants.UPDATE_SUCCESS, result);
    }

    @Test
    public void whenUpdateBook_withBookNameIsNull_thenReturnBookNameInvalid(){
        BookDTO bookDTO = new BookDTO(1,"", "Harry Poster Book's Desc", "No Name", "bookcover.png", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_NAME_INVALID, result);
    }

    @Test
    public void whenUpdateBook_withBookAuthorIsNull_thenReturnBookAuthorInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "", "bookcover.png", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_AUTHOR_INVALID, result);
    }

    @Test
    public void whenUpdateBook_withBookDescIsNull_thenReturnBookDescInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "", "No Name", "bookcover.png", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_DESC_INVALID, result);
    }

    @Test
    public void whenUpdateBook_withBookCoverIsNull_thenReturnBookCoverInvalid(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "No Name", "", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_COVER_INVALID, result);
    }

    @Test
    public void whenUpdateBook_withBookCoverIsNotFormat_thenReturnBookCoverIsNotFormat(){
        BookDTO bookDTO = new BookDTO(1,"Harry porter", "Harry Poster Book's Desc", "No Name", "abcdef", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_COVER_IS_NOT_FORMAT, result);
    }

    @Test
    public void whenUpdateBook_withBookIdIsNotExisted_thenReturnBookIsNotExist(){
        BookDTO bookDTO = new BookDTO(10000,"Harry porter", "Harry Poster Book's Desc", "No Name", "abcdef.jpg", "Novel", 1);
        String result = bookService.updateBook(bookDTO);

        assertEquals(ErrorMessageConstants.BOOK_IS_NOT_EXISTS, result);
    }

    /*--------- Delete Book(int BookId)--------*/
    @Test
    public void whenDeleteBook_withBookIdIsValid_thenReturnDeleteSuccess(){
        int bookId = 96;
        String result = bookService.deleteBook(bookId);
        assertEquals(SuccessMessageContants.DELETE_SUCCESS, result);
    }

    @Test
    public void whenDeleteBook_withBookIdIsNotExist_thenReturnBookIsNotExistError(){
        String result = "";
        int bookId = 1000;
        try {
            result = bookService.deleteBook(bookId);
        } catch (EmptyResultDataAccessException e){
            assertEquals(ErrorMessageConstants.BOOK_IS_NOT_EXISTS, result);
        }
    }

    /*--------- detailBookById(int BookId)--------*/
    @Test
    public void whenGetDetailBookById_withBookIdIsInvalid_thenReturnNull(){
        BookDTO bookDTO = new BookDTO();
        int bookId = 1000;
        try {
            bookDTO = bookService.detailBookById(bookId);
        } catch (Exception e){
            assertEquals(null, bookDTO);
        }
    }
}
