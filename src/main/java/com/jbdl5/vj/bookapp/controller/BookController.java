package com.jbdl5.vj.bookapp.controller;

import com.jbdl5.vj.bookapp.dto.BookDto;
import com.jbdl5.vj.bookapp.model.Book;
import com.jbdl5.vj.bookapp.service.BookServiceImpl;
import com.jbdl5.vj.bookapp.util.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(
    path = Constants.ROOT_URL,
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class BookController {

  private static Logger logger = LoggerFactory.getLogger(BookController.class);

  @Autowired BookServiceImpl bookService;

  @Autowired Environment environment;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping
  public ResponseEntity<Map> getEnvProperties() {
    Map<String, String> props = new HashMap<>();
    props.put("server.port", serverPort);
    return new ResponseEntity(props, HttpStatus.OK);
  }

  @GetMapping(path = Constants.BOOK_URL)
  public ResponseEntity<List<BookDto>> getBooks() {
    List<BookDto> books =
        bookService.getBooks().stream()
            .map(book -> BookDto.toDto(book))
            .collect(Collectors.toList());
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping(path = Constants.BOOKID_URL)
  public ResponseEntity<BookDto> getBookByBookId(
      @PathVariable(name = "bookId", required = true) @NotBlank Long bookId) {

    Book book = bookService.getBookById(bookId);
    if (book != null) {
      return new ResponseEntity<>(BookDto.toDto(book), HttpStatus.OK);
    } else {
      return new ResponseEntity("BOOK NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(path = Constants.BOOK_URL)
  public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
    bookService.createBook(Book.toEntity(bookDto));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping(path = Constants.BOOK_URL)
  public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {

    if (bookService.isBookExists(bookDto.getBookId())) {
      Book bookUpdated = bookService.updateBook(Book.toEntity(bookDto));
      return new ResponseEntity<>(BookDto.toDto(bookUpdated), HttpStatus.OK);
    } else {
      return new ResponseEntity("BOOK NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = Constants.BOOKID_URL)
  public ResponseEntity<Boolean> deleteBook(
      @PathVariable(name = "bookId", required = true) @NotBlank Long bookId) {
    if (bookService.isBookExists(bookId)) {
      return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
    } else {
      return new ResponseEntity("BOOK NOT FOUND", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = Constants.BOOKCOST_URL)
  public ResponseEntity<Integer> deleteBooksOverCost(
      @PathVariable(name = "cost", required = true) @NotBlank Integer cost) {
    Integer deleteCnt = bookService.deleteBooksOverCost(cost);
    return new ResponseEntity(deleteCnt, HttpStatus.NOT_FOUND);
  }
}
