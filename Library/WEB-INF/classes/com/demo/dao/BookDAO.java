package com.demo.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.demo.bean.BookBean;
import com.demo.bean.BorrowedBooksBean;


public class BookDAO {

  public List<BookBean> findBooksByTitle(String title) {
    // TODO Auto-generated method stub
    List<BookBean> l = new LinkedList<BookBean>();
    BookBean bookbean = new BookBean();
    Connection conn = null;
    try {
      conn = DataConnect.getConnection();
      String s = "SELECT bookid,title,author,publisher FROM Book WHERE title =?";
      PreparedStatement pstmt = conn.prepareStatement(s);
      pstmt.setString(1, title);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        bookbean.setBookid(rs.getString(1));
        bookbean.setTitle(rs.getString(2));
        bookbean.setAuthor(rs.getString(3));
        bookbean.setPublisher(rs.getString(4));
        l.add(bookbean);
      }
    }catch(SQLException e) {
      System.err.println("BookDAO Error -->");
      System.out.println(e.getMessage());
    }finally {
      DataConnect.close(conn);
    }
    
    
    
    
    return l;
  }

  public boolean addBook(BookBean bookbean) {
    // TODO Auto-generated method stub
    
    int rows = 0;
    
    Connection conn = null;
    PreparedStatement pstmt  = null;
    String s = "INSERT INTO Book(bookid,title,author,publisher) VALUES(?,?,?,?)";
    try {
      conn = DataConnect.getConnection();
      
      pstmt = conn.prepareStatement(s);
      pstmt.setString(1, bookbean.getBookid());
      pstmt.setString(2, bookbean.getTitle());
      pstmt.setString(3, bookbean.getAuthor());
      pstmt.setString(4, bookbean.getPublisher());
      rows = pstmt.executeUpdate();
      
    }catch(SQLException e) {
      System.err.println("BookDAO Error -->");
      System.out.println(e.getMessage());
    }finally {
      DataConnect.close(conn);
    }
    
    
    return (rows>0);
  }

  public boolean removeBook(String bookid) {

    int rows = 0;
    
    Connection conn = null;
    PreparedStatement pstmt  = null;
    String s = "DELETE FROM Book WHERE bookid = ?";
    try {
      conn = DataConnect.getConnection();
      
      pstmt = conn.prepareStatement(s);
      pstmt.setString(1, bookid);
     
      rows = pstmt.executeUpdate();
      
    }catch(SQLException e) {
      System.err.println("BookDAO Error -->");
      System.out.println(e.getMessage());
    }finally {
      DataConnect.close(conn);
    }
    
    return (rows>0);
    
  }
  


}
