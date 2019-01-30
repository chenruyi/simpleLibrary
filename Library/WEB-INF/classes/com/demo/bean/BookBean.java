package com.demo.bean;

public class BookBean {
  private String bookid = null;
  private String title = null;
  private String author = null;
  private String publisher = null;
  
 public String toJSON() {
   
   return String.format("{\"bookid\":\"%s\", \"title\":\"%s\", \"author\":\"%s\", \"publisher\":\"%s\"}", getBookid(),getTitle(),getAuthor(),getPublisher());
    
 }
  
  public String getBookid() {
    return bookid;
  }
  public void setBookid(String bookid) {
    this.bookid = bookid;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getPublisher() {
    return publisher;
  }
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }
}
