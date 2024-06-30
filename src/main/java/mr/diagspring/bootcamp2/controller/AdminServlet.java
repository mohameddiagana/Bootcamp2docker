package mr.diagspring.bootcamp2.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.diagspring.bootcamp2.dto.UserDTO;
import mr.diagspring.bootcamp2.service.IUserService;
import mr.diagspring.bootcamp2.service.UserService;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {

	private IUserService userservice = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("usersList", userservice.getAll());/* RECUPERATION DES USERS AU NIVEAU DE LA BASE */

		;
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String LastName = req.getParameter("LastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean state = Boolean.valueOf(req.getParameter("state"));

		UserDTO userDto = new UserDTO();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setState(state);

		Optional<UserDTO> user = userservice.save(userDto);
		if (user.isEmpty()) {
			// TODO LOGGER
		} else {
			// TODO LOGGER
		}
		doGet(req, resp);
	}
}
