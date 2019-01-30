package com.demo.bean;

import java.sql.Date;

public class BorrowedBooksBean {
        private String bookid=null;
        private String userId=null;
        private Date time=null;
        
        public String toJSON() {
          return String.format("{ \"bookid\":\"%s\", \"userId\" : \"%s\", \"time\":\"%s\" }", getBookid(), getUserId(), getTime().toString());
        }
        
        public String getBookid() {
          return bookid;
        }
        public void setBookid(String bookid) {
          this.bookid = bookid;
        }
        public String getUserId() {
          return userId;
        }
        public void setUserId(String userId) {
          this.userId = userId;
        }
        public Date getTime() {
          return time;
        }
        public void setTime(Date time) {
          this.time = time;
        }
      
        
        
        
        
}
