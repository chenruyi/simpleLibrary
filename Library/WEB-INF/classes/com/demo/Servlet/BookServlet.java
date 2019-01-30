package com.demo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.bean.BookBean;
import com.demo.dao.BookDAO;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String Operation = request.getParameter("Operation");
	  response.setCharacterEncoding("utf-8");
	  PrintWriter out= response.getWriter();
      
		BookBean bookbean = new BookBean();
		BookDAO bookdao = new BookDAO();
		switch(Operation) {
		  case "DELETE": {
		    String bookid = request.getParameter("bookid");
		    boolean r = bookdao.removeBook(bookid);
		    
		      if(r) {
		        out.write("succeed");
		      }else {
		      out.write("failed");
		      }
		    break;
		  }
		  case "INSERT": {
		    String bookid = request.getParameter("bookid");
	            String title = request.getParameter("title");
	            String author = request.getParameter("author");
	            String publisher = request.getParameter("publisher");
	            
	            bookbean.setBookid(bookid);
	            bookbean.setTitle(title);
	            bookbean.setAuthor(author);
	            bookbean.setPublisher(publisher);
	            boolean r = bookdao.addBook(bookbean);
	            if(r) {
	              out.write("succeed");
	            }else {
	            out.write("failed");
	            }
	            
		    break;
		  }
		  case "SELECT":{
		    String title = request.getParameter("title");
		    List<BookBean> l = bookdao.findBooksByTitle(title);
		    
		    
		    if(l.isEmpty()) {
		        out.write("{}");
		      }else {
		      
		      out.print("{ \"result\": ");
		    for(BookBean b: l) {
		       out.print(b.toJSON());
		     }
		      out.println("}");
		      }
		   
		    break;
		  }
		  default: {
		    
		  }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
