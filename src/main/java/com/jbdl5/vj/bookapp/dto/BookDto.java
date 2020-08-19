package com.jbdl5.vj.bookapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jbdl5.vj.bookapp.model.Book;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

  private long bookId;

  private String name;

  private String author;

  private Integer cost;

  public BookDto() {}

  public BookDto(long bookId, String name, String author, Integer cost) {
    this.bookId = bookId;
    this.name = name;
    this.author = author;
    this.cost = cost;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public static BookDto toDto(Book book) {
    BookDto bookDto =
        new BookDto(book.getId(), book.getName(), book.getAuthorName(), book.getCost());

    return bookDto;
  }
}
