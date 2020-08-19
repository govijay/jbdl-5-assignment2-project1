package com.jbdl5.vj.bookapp.service;

import com.jbdl5.vj.bookapp.model.Book;
import java.util.List;

public interface BookService {

  void createBook(Book book);

  Book updateBook(Book book);

  boolean deleteBook(Long bookId);

  Integer deleteBooksOverCost(Integer cost);

  boolean isBookExists(Long bookId);

  Book getBookById(Long bookId);

  List<Book> getBooks();
}
