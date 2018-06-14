package com.example.demo.services;

import com.example.demo.constants.ErrorMessageConstants;
import com.example.demo.constants.SuccessMessageContants;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.models.BookDTO;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private String checkModelValid(Book book){
        if (book.getBookName().equals("") || book.getBookName() == null){
            return ErrorMessageConstants.BOOK_NAME_INVALID;
        }

        if (book.getBookAuthor().equals("") || book.getBookAuthor() == null){
            return ErrorMessageConstants.BOOK_AUTHOR_INVALID;
        }

        if (book.getBookDesc().equals("") || book.getBookDesc() == null){
            return ErrorMessageConstants.BOOK_DESC_INVALID;
        }

        if (book.getBookCover().equals("") || book.getBookCover() == null){
            return ErrorMessageConstants.BOOK_COVER_INVALID;
        }

        return SuccessMessageContants.MODEL_VALID;
    }

    @Override
    public List<BookDTO> getAllBooks(){
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        List<BookDTO> listbooks = new ArrayList<>();
        for(Book book: books){
            BookDTO bookDTO = getBookDTO(book);
            listbooks.add(bookDTO);
        }
        System.out.println(listbooks);
        return listbooks;
    }

    @Override
    public BookDTO getBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setBookAuthor(book.getBookAuthor());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setBookDesc(book.getBookDesc());
        bookDTO.setCategoryName(book.getCategory().getCategoryName());
        bookDTO.setBookCover(book.getBookCover());
        return bookDTO;
    }

    @Override
    public BookDTO detailBookById(int bookId) throws NullPointerException{
        Book item = bookRepository.findOne(bookId);
        if ( item == null){
            return null;
        } else {
            BookDTO bookDTO = getBookDTO(item);
            return  bookDTO;
        }
    }

    public Book getBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookName(bookDTO.getBookName());
        book.setBookAuthor(bookDTO.getBookAuthor());
        book.setBookCover(bookDTO.getBookCover());
        book.setBookDesc(bookDTO.getBookDesc());
        book.setCategory(categoryRepository.findByCategoryName(bookDTO.getCategoryName().trim()));
        return book;
    }

    public Book getBookUpdate(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookId(bookDTO.getBookId());
        book.setBookName(bookDTO.getBookName());
        book.setBookAuthor(bookDTO.getBookAuthor());
        book.setBookCover(bookDTO.getBookCover());
        book.setBookDesc(bookDTO.getBookDesc());
        book.setCategory(categoryRepository.findByCategoryName(bookDTO.getCategoryName().trim()));
        return book;
    }

    @Override
    public List<BookDTO> getListBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        List<BookDTO> listbooks = new ArrayList<BookDTO>();
        for (Book book : books) {
            BookDTO bookDTO = getBookDTO(book);
            listbooks.add(bookDTO);
        }
        return listbooks;
    }

    @Override
    public String addBook(BookDTO bookDTO) {

        if (bookDTO.getBookName().equals("") ){
            return ErrorMessageConstants.BOOK_NAME_INVALID;
        }
        if (categoryRepository.findByCategoryName(bookDTO.getCategoryName().trim()) == null) {
            Category category = new Category();
            category.setCategoryName(bookDTO.getCategoryName());
            categoryRepository.save(category);
        }
        Book book = getBook(bookDTO);
        String checkModelValid = checkModelValid(book);
        if(checkModelValid.equals(SuccessMessageContants.MODEL_VALID)){
            bookRepository.save(book);
            return SuccessMessageContants.CREATE_SUCCESS;
        } else {
            return checkModelValid;
        }
    }

    @Override
    public String updateBook(BookDTO bookDTO) {
        if (categoryRepository.findByCategoryName(bookDTO.getCategoryName().trim()) == null) {
            Category category = new Category();
            category.setCategoryName(bookDTO.getCategoryName());
            categoryRepository.save(category);
        }
        Book book = getBookUpdate(bookDTO);
        String checkModelValid = checkModelValid(book);
        if(checkModelValid.equals(SuccessMessageContants.MODEL_VALID)){
            if(bookRepository.findOne(book.getBookId()) == null){
                return ErrorMessageConstants.BOOK_IS_NOT_EXISTS;
            }
            bookRepository.save(book);
            return SuccessMessageContants.UPDATE_SUCCESS;
        } else {
            return checkModelValid;
        }
    }

    @Override
    public String deleteBook(int bookId) {
        if(bookRepository.findOne(bookId) == null){
            return ErrorMessageConstants.BOOK_IS_NOT_EXISTS;
        }
        bookRepository.delete(bookId);
        return SuccessMessageContants.DELETE_SUCCESS;
    }
}
