package com.demo.Servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.demo.bean.UserBean;
import com.demo.dao.UserDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String role = request.getParameter("role");
    
    String userId = request.getParameter("userId");
    String password = request.getParameter("password");
    UserDAO userdao = new UserDAO();
    
    if(role.equals("admin")) {
      UserBean admin = userdao.getAdmin(userId, password);
      
      if (admin != null) {
        //  login succeed return to admin page
        request.getSession().setAttribute("user", admin);
        
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
      }else {
        // return to login page
      
        response.sendRedirect("admin.html");
      }
      
    }else {
    
    UserBean user = userdao.getUser(userId, password);
    
    if (user != null) {
     
      //  login succeed return to user page
      request.getSession().setAttribute("user", user);
      request.getRequestDispatcher("/user.jsp").forward(request, response);
      
    }else {
      // return to login page
     
      response.sendRedirect("login.html");
    
    }
    }
    
    
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    doGet(req,resp);
  }
  
  
  
}
