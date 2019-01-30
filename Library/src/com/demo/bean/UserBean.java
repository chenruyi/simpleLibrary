package com.demo.bean;

public class UserBean {
  private String email;
  private String password;
  private String userId;
  
  public String toJSON() {
    return String.format("{\"userId\":\"%s\", \"email\":\"%s\"}", getUserId(),getEmail());
    
  }
  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
}
