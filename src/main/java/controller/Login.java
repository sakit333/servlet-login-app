package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Userdao;
import dto.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		User u = new User();
		u.setEmail(email);
		u.setPassword(password);

		Userdao dao = new Userdao();
		boolean flag = dao.loginUser(email, password);
		
		if (flag) {	
			req.getRequestDispatcher("home.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 style='color: red; text-align: center; font-size: 16px;'>Invalid ID or Password</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}

	}
}
