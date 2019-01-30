package com.demo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.demo.bean.UserBean;
import com.demo.dao.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  
	  String Operation = request.getParameter("Operation");
	  response.setCharacterEncoding("utf-8");
      PrintWriter out= response.getWriter();
	  UserBean userbean = new UserBean();
      UserDAO userdao = new UserDAO();
      switch(Operation) {
        case "DELETE": {
          String userId = request.getParameter("userId");
          boolean r = userdao.removeUser(userId);
          
          if(r) {
            out.write("succeed");
          }else {
          out.write("failed");
          }
          break;
        }
        case "INSERT": {
          
              String email = request.getParameter("email");
              String password = request.getParameter("password");
              String userId = request.getParameter("userId");
              userbean.setEmail(email);
              userbean.setPassword(password);
              userbean.setUserId(userId);
              
              boolean r = userdao.addUser(userbean);

              if(r) {
                out.write("succeed");
              }else {
              out.write("failed");
              }
          break;
        }
        case "SELECT":{
          String userId = request.getParameter("userId");
          userbean = userdao.findUserByUserId(userId);
          if(userbean==null) {
            out.write("{}");
          }else {
            out.write("{\"result\":");
          out.write(userbean.toJSON());
          out.write("}");
          }
          break;
        }
        case "CHANGEPASSWORD":{
          String userId = request.getParameter("userId");
          String oldpassword = request.getParameter("oldpassword");
          String newpassword = request.getParameter("newpassword");
          boolean r = userdao.updatePassword(userId,oldpassword,newpassword);
         
          if(r) {
            response.sendRedirect("logout");
          }else {
           out.print("failed");
          }
          break;
        }case "CHANGEEMAIL":{
          String userId = request.getParameter("userId");
          
          String newemail = request.getParameter("email");
          boolean r = userdao.updateEmail(userId,newemail);
         
          if(r) {
            out.println("succeed");
          }else {
           out.print("failed");
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
