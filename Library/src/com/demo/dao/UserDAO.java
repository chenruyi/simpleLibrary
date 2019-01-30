package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.demo.bean.UserBean;

public class UserDAO {

 
  
  // 根据用户名查询用户
  public UserBean getUser(String userId, String password) {
    Connection con = null;
    PreparedStatement pstmt = null;
    UserBean userbean = null;
    try {
      con = DataConnect.getConnection();
      pstmt = con.prepareStatement("Select userId,email from user where userId=? and password=?");
      pstmt.setString(1, userId);
      pstmt.setString(2, password);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        userbean = new UserBean();
        userbean.setUserId(rs.getString(1));
        userbean.setEmail(rs.getString(2));
       
        
      }
    }catch (SQLException e) {
      System.err.println("UserDAO error --");
      System.out.println(e.getMessage());
    }finally {
      DataConnect.close(con);
    }
    
    return userbean;
  }
  
//根据用户名查询管理员
 public UserBean getAdmin(String userId, String password) {
   Connection con = null;
   PreparedStatement pstmt = null;
   UserBean userbean = null;
   try {
     con = DataConnect.getConnection();
     pstmt = con.prepareStatement("Select userId,email from admin where userId=? and password=?");
     pstmt.setString(1, userId);
     pstmt.setString(2, password);
     ResultSet rs = pstmt.executeQuery();
     if (rs.next()) {
       userbean = new UserBean();
       userbean.setUserId(rs.getString(1));
       userbean.setEmail(rs.getString(2));
      
       
     }
   }catch (SQLException e) {
     System.err.println("UserDAO error --");
     System.out.println(e.getMessage());
   }finally {
     DataConnect.close(con);
   }
   
   return userbean;
 }

public boolean removeUser(String userId) {
  // TODO Auto-generated method stub
  int rows = 0;
  Connection conn = null;
  PreparedStatement pstmt  = null;
  String s = "DELETE FROM user WHERE userId = ?";
  try {
    conn = DataConnect.getConnection();
    
    pstmt = conn.prepareStatement(s);
    pstmt.setString(1, userId);
   
    rows = pstmt.executeUpdate();
    
  }catch(SQLException e) {
    System.err.println("UserDAO Error -->");
    System.out.println(e.getMessage());
  }finally {
    DataConnect.close(conn);
  }
  return (rows>0);
}

public boolean addUser(UserBean userbean) {
  // TODO Auto-generated method stub

  int rows = 0;
  
  Connection conn = null;
  PreparedStatement pstmt  = null;
  String s = "INSERT INTO user(email,password,userId) VALUES(?,?,?)";
  try {
    conn = DataConnect.getConnection();
    
    pstmt = conn.prepareStatement(s);
    pstmt.setString(1, userbean.getEmail());
    pstmt.setString(2, userbean.getPassword());
    pstmt.setString(3, userbean.getUserId());

    rows = pstmt.executeUpdate();
    
  }catch(SQLException e) {
    System.err.println("UserDAO Error -->");
    System.out.println(e.getMessage());
  }finally {
    DataConnect.close(conn);
  }
  
  
  return (rows>0);
}

public UserBean findUserByUserId(String userId) {
  UserBean userbean =null; 
  Connection conn = null;
  try {
    conn = DataConnect.getConnection();
    String s = "SELECT userId,email FROM user WHERE userId =?";
    PreparedStatement pstmt = conn.prepareStatement(s);
    pstmt.setString(1, userId);
    ResultSet rs = pstmt.executeQuery();
    if(rs.next()) {
      userbean =new UserBean();
      userbean.setUserId(rs.getString(1));
      userbean.setEmail(rs.getString(2));
    }
  }catch(SQLException e) {
    System.err.println("UserDAO Error -->");
    System.out.println(e.getMessage());
  }finally {
    DataConnect.close(conn);
  }
  
  
  
  
  return userbean;
}


public boolean updateEmail(String userId, String newemail) {
  Connection con = null;
  PreparedStatement pstmt = null;
  int rows=0;
  try {
    con = DataConnect.getConnection();
    pstmt = con.prepareStatement("Update user SET email=? WHERE userId=?");
    pstmt.setString(1, newemail);
    pstmt.setString(2, userId);
    
    rows = pstmt.executeUpdate();
    
  }catch (SQLException e) {
    System.err.println("UserDAO error --");
    System.out.println(e.getMessage());
  }finally {
    DataConnect.close(con);
  }
  
  
  
  return rows>0;
}

public boolean updatePassword(String userId, String oldpassword, String newpassword) {
  Connection con = null;
  PreparedStatement pstmt = null;
  int rows=0;
  try {
    con = DataConnect.getConnection();
    pstmt = con.prepareStatement("Update user SET password=? WHERE userId=? and password=?");
    pstmt.setString(1, newpassword);
    pstmt.setString(2, userId);
    pstmt.setString(3, oldpassword);
    rows = pstmt.executeUpdate();
    
  }catch (SQLException e) {
    System.err.println("UserDAO error --");
    System.out.println(e.getMessage());
  }finally {
    DataConnect.close(con);
  }
  
  
  
  return rows>0;
}



}
