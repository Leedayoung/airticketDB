

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(Start.class.getName());
		logger.info("Sublet connected");
	
		RequestDispatcher view;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.info("JDBC Driver OK");
		}
		catch(ClassNotFoundException exc) {
			logger.log(Level.SEVERE, "Can't find JDBC driver", exc);
			view = request.getRequestDispatcher("/WEB-INF/Fail.jsp");
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?serverTimezone=UTC", "student", "student");
			view = request.getRequestDispatcher("/WEB-INF/Test.jsp");
			logger.log(Level.INFO, "DB Connected");
		}
		catch(SQLException exc) {
			logger.log(Level.SEVERE, "JDBC Error");
			exc.printStackTrace();
			view = request.getRequestDispatcher("/WEB-INF/Fail.jsp");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
