

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterStudent
 */
@WebServlet("/RegisterStudent")
public class ReviewerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ServiceDB dbService;     
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public RegisterStudent() {
//       super();
//    }
	public void init() throws ServletException {
        dbService = new ServiceDB();
/*        try {
			dbService.getConnected();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}

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
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("loginname");    
		String email = request.getParameter("email");    
		String password = request.getParameter("password");   
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	    String currentDate=sdf.format(date );
		Student aStudent = new Student(email, userid, password, currentDate);		
		dbService.addStudent(aStudent);

		getServletContext().getRequestDispatcher("/Actions.jsp").forward(request, response);		
	}
}


