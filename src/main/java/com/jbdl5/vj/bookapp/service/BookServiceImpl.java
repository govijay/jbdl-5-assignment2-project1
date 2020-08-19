package com.jbdl5.vj.bookapp.service;

import com.jbdl5.vj.bookapp.dao.BookDao;
import com.jbdl5.vj.bookapp.model.Book;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

  private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

  @Autowired BookDao bookDao;

  @Override
  public void createBook(Book book) {
    bookDao.save(book);
  }

  @Override
  public Book updateBook(Book book) {
    if (book != null) {
      // update 'Will' logic
      String authorName = book.getAuthorName();
      book.setAuthorName(checkFirstName(authorName));
      bookDao.save(book);
    }
    return bookDao.getBookById(book.getId());
  }

  private String checkFirstName(String authorName) {

    String[] splitNames =
        Arrays.stream(authorName.split(" ")).map(String::trim).toArray(String[]::new);

    if(splitNames[0].equals("Will")){
      return "William";
    }

    return authorName;
  }

  @Override
  public boolean deleteBook(Long bookId) {
    if (bookId != null) {
      Book bookToDel = bookDao.getBookById(bookId);
      bookDao.delete(bookToDel);
      return true;
    }
    return false;
  }

  @Override
  public Integer deleteBooksOverCost(Integer cost) {
    return bookDao.deleteBooksOverCost(cost);
  }

  @Override
  public boolean isBookExists(Long bookId) {
    if (bookId != null) {
      return (bookDao.getBookById(bookId) == null) ? false : true;
    }
    return false;
  }

  @Override
  public Book getBookById(Long bookId) {
    return bookDao.getBookById(bookId);
  }

  @Override
  public List<Book> getBooks() {
    return bookDao.findAll();
  }
}
