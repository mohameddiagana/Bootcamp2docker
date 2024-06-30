package mr.diagspring.bootcamp2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");

		log.info("votre mail est : {}", email);
		req.getSession().setAttribute("username", email);
		resp.sendRedirect("welcome");

	}

}
