package com.jbdl5.vj.bookapp.model;


import com.jbdl5.vj.bookapp.dto.BookDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "AUTHOR")
  private String authorName;

  @Column(name = "COST")
  private Integer cost;

  @Column(name = "TIMESTAMP")
  private LocalDateTime timeStamp;

  public Book() {
  }

  public Book(long id, String name, String authorName, Integer cost, LocalDateTime timeStamp) {
    this.id = id;
    this.name = name;
    this.authorName = authorName;
    this.cost = cost;
    this.timeStamp = timeStamp;
  }

  public long getId() {
    return id;
  }

  public void setId(long bookId) {
    this.id = bookId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public static Book toEntity(BookDto bookDto) {
    return new Book(
        bookDto.getBookId(),
        bookDto.getName(),
        bookDto.getAuthor(),
        bookDto.getCost(),
        LocalDateTime.now()
        );
  }

  @Override
  public String toString() {
    return "Book{" +
        "bookId=" + id +
        ", name='" + name + '\'' +
        ", authorName='" + authorName + '\'' +
        ", cost=" + cost +
        ", timeStamp=" + timeStamp +
        '}';
  }
}
