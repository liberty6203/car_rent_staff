package edu.tjut.lzy.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.tjut.lzy.dao.DBUtil;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String staffid = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("select * from staff where staffid = '"+staffid+"' and password='"+password+"'");
		ResultSet rSet = DBUtil.execQuery("select * from staff where staffid = '"+staffid+"' and password='"+password+"'");
		try {
			if(rSet.next()){
				HttpSession session = request.getSession();
				session.setAttribute("staffid", staffid);
				String resp =  "{\"status\":"+0+",\"msg\":\""+"登陆成功"+"\"}";
				response.getWriter().write(resp);
			}else{
				String resp =  "{\"status\":"+1+",\"msg\":\""+"用户名或密码错误"+"\"}";
				response.getWriter().write(resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String resp =  "{\"status\":"+1+",\"msg\":\""+" server error"+"\"}";
			response.getWriter().write(resp);
		}
	}

}
