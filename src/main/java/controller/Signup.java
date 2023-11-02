package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Userdao;
import dto.User;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone"));
		String password = req.getParameter("password");
		
		User u = new User();
		u.setName(name);
		u.setPhno(phone);
		u.setEmail(email);
		u.setPassword(password);

		Userdao dao = new Userdao();
		dao.addUser(u);

		req.getRequestDispatcher("login.html").include(req, resp);
	}
}
