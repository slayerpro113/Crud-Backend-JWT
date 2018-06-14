package com.example.demo.models;

public class BookDTO {

    private int bookId;

    private String bookName;

    private String bookDesc;

    private String bookAuthor;

    private String bookCover;

    private String categoryName;

    private int categoryId;

    public BookDTO() {
    }

    public BookDTO(int bookId, String bookName, String bookDesc, String bookAuthor, String bookCover, String categoryName, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookDesc = bookDesc;
        this.bookAuthor = bookAuthor;
        this.bookCover = bookCover;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
