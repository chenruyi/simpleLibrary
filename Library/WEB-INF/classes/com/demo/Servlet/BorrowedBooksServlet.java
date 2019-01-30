package com.demo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.demo.bean.BorrowedBooksBean;
import com.demo.dao.BorrowedBooksDAO;

/**
 * Servlet implementation class BorrowedBooksServlet
 */
@WebServlet("/BorrowedBooksServlet")
public class BorrowedBooksServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public BorrowedBooksServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub

    String Operation = request.getParameter("Operation");
    response.setCharacterEncoding("utf-8");
    PrintWriter out = response.getWriter();
    BorrowedBooksDAO bbdao = new BorrowedBooksDAO();
    switch (Operation) {
      case "SELECTALL": {
        
        List<BorrowedBooksBean> l = bbdao.findAllBorrowedBooks();
        
          out.write("{\"result\":{");
          for (int i=0;i<l.size();i++) {
            out.write(String.format("\"%d\" : %s",i,l.get(i).toJSON()));
            if(i<l.size()-1)
              out.write(",");
          }
          out.write("}}");

        
        break;
      }
      case "SELECT": {
        String userId = request.getParameter("userId");
        List<BorrowedBooksBean> l = bbdao.findBorrowedBooksByUserId(userId);

        out.write("{\"result\":{");
        for (int i=0;i<l.size();i++) {
          out.write(String.format("\"%d\" : %s",i,l.get(i).toJSON()));
          if(i<l.size()-1)
            out.write(",");
        }
        out.write("}}");


        
        break;
      }
      case "DELETE": {

        String bookid = request.getParameter("bookid");

        boolean r = bbdao.removeBorrowBooks(bookid);
        if (r) {
          out.write("succeed");
        } else {
          out.write("failed");
        }
        break;
      }
      case "INSERT": {
        String bookid = request.getParameter("bookid");
        String userId = request.getParameter("userId");
        java.util.Date c = new java.util.Date();
        Date date = new Date(c.getTime());
        BorrowedBooksBean bdb = new BorrowedBooksBean();
        bdb.setBookid(bookid);

        bdb.setUserId(userId);
        bdb.setTime(date);
        

        boolean r = bbdao.addBorrowBooks(bdb);
        if (r) {
          out.write("succeed");
        } else {
          out.write("failed");
        }
        break;
      }
      default: {

      }
    }



    // out.write("error, not input");

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }



}
