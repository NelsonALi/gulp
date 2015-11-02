import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/LoginIn")
public class LoginIn extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ServiceDB dbService = new ServiceDB();
		String userid = request.getParameter("loginname");    
/*	    if (!dbService.validReviewer(userid)) {
	    	//please register first
	    	getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
	    } else {
	    	// do actions: 
	    	getServletContext().getRequestDispatcher("/Actions.jsp").forward(request, response);
	    }*/
    	getServletContext().getRequestDispatcher("/RateInput.jsp").forward(request, response);
	}

}
