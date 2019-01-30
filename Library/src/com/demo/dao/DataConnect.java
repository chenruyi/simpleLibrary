package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
  public static Connection getConnection() {
    try {
   //   Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B8", "chen", "password1234");
      return con;
    }catch(Exception e) {
      System.err.println("Database.getConnection() Error -- ");
      System.out.println(e.getMessage());
    }
    return null;
  }
  
  public static void close(Connection con) {
    try {
      con.close();
    }catch(Exception e) {}
  }
}
