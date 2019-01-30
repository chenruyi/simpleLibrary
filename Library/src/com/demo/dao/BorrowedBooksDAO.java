package com.demo.dao;

import com.demo.bean.BorrowedBooksBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class BorrowedBooksDAO {

  public boolean addBorrowBooks(BorrowedBooksBean bdb) {

    int rows = 0;

    Connection conn = null;
    PreparedStatement pstmt = null;
    String s = "INSERT INTO borrowedbooks(bookid,userId,time) VALUES(?,?,?)";
    try {
      conn = DataConnect.getConnection();

      pstmt = conn.prepareStatement(s);
      pstmt.setString(1, bdb.getBookid());
      pstmt.setString(2, bdb.getUserId());
      pstmt.setDate(3, bdb.getTime());

      rows = pstmt.executeUpdate();

    } catch (SQLException e) {
      System.err.println("UserDAO Error -->");
      System.out.println(e.getMessage());
    } finally {
      DataConnect.close(conn);
    }


    return (rows > 0);


  }



  public boolean removeBorrowBooks(String bookid) {
    int rows = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String s = "DELETE FROM borrowedbooks WHERE bookid=?";
    try {
      conn = DataConnect.getConnection();

      pstmt = conn.prepareStatement(s);
      pstmt.setString(1, bookid);

      rows = pstmt.executeUpdate();

    } catch (SQLException e) {
      System.err.println("UserDAO Error -->");
      System.out.println(e.getMessage());
    } finally {
      DataConnect.close(conn);
    }
    return (rows > 0);

  }



  public BorrowedBooksBean findBorrowedBooksByBookId(String bookid) {
    // TODO Auto-generated method stub

    BorrowedBooksBean bdb = null;
    Connection conn = null;
    try {
      conn = DataConnect.getConnection();
      String s = "SELECT bookid,userId,time,renew, FROM borrowedbooks WHERE bookid =?";
      PreparedStatement pstmt = conn.prepareStatement(s);
      pstmt.setString(1, bookid);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        bdb = new BorrowedBooksBean();
        bdb.setBookid(rs.getString(1));
        bdb.setUserId(rs.getString(2));
        bdb.setTime(rs.getDate(3));

      }
    } catch (SQLException e) {
      System.err.println("UserDAO Error -->");
      System.out.println(e.getMessage());
    } finally {
      DataConnect.close(conn);
    }

    return bdb;

  }


  public List<BorrowedBooksBean> findBorrowedBooksByUserId(String UserId) {
    // TODO Auto-generated method stub
    List<BorrowedBooksBean> l = new LinkedList<BorrowedBooksBean>();
    BorrowedBooksBean bdb = new BorrowedBooksBean();
    Connection conn = null;
    try {
      conn = DataConnect.getConnection();
      String s = "SELECT bookid,userId,time FROM borrowedbooks WHERE userId =?";
      PreparedStatement pstmt = conn.prepareStatement(s);
      pstmt.setString(1, UserId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        bdb = new BorrowedBooksBean();
        bdb.setBookid(rs.getString(1));
        bdb.setUserId(rs.getString(2));
        bdb.setTime(rs.getDate(3));

        l.add(bdb);
      }
    } catch (SQLException e) {
      System.err.println("UserDAO Error -->");
      System.out.println(e.getMessage());
    } finally {
      DataConnect.close(conn);
    }

    return l;

  }

  public List<BorrowedBooksBean> findAllBorrowedBooks() {
    // TODO Auto-generated method stub
    List<BorrowedBooksBean> l = new LinkedList<BorrowedBooksBean>();
    BorrowedBooksBean bdb = null;
    Connection conn = null;
    try {
      conn = DataConnect.getConnection();
      String s = "SELECT bookid,userId,time FROM borrowedbooks";
      PreparedStatement pstmt = conn.prepareStatement(s);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        bdb = new BorrowedBooksBean();
        bdb.setBookid(rs.getString(1));
        bdb.setUserId(rs.getString(2));
        bdb.setTime(rs.getDate(3));

        l.add(bdb);
      }
    } catch (SQLException e) {
      System.err.println("BorrowBooksDAO Error -->");
      System.out.println(e.getMessage());
    } finally {
      DataConnect.close(conn);
    }

    return l;

  }



}
