

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("CName");
		String age_text = request.getParameter("CAge");
		String gender_text = request.getParameter("CGender");
		String nation = request.getParameter("CNation");
		
		int age;
		boolean gender;

		try {
			if (name == null || age_text == null || gender_text == null || nation == null)
				throw new IllegalArgumentException("Illegal Name");
			
			try {
				age = Integer.valueOf(age_text);
			}
			catch (NumberFormatException exc) {
				throw new IllegalArgumentException("Illegal Age");
			}
			
			switch (gender_text) {
			case "male":
				gender = true;
				break;

			case "female":
				gender = false;
				break;

			default:
				throw new IllegalArgumentException("Illegal Gender");
			}
		}
		catch (IllegalArgumentException exc) {
			response.sendRedirect("/airticketDB/index.html");
			return;
		}
		
		Logger logger = Logger.getLogger(Signup.class.getName());

		Connection conn = null;
		Integer cid = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/11739592_airticketDB?serverTimezone=UTC", "student", "student");
			
			Statement Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery("SELECT MAX(CID) FROM client");

			if (rs.next())
				cid = rs.getInt(1);
				if (cid != null)
					cid++;

			if (cid == null)
				cid = 1;
			
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO client(CID, CName, CAge, CGender, CNation) VALUES (?,?,?,?,?)");
			pStmt.setInt(1, cid);
			pStmt.setString(2, name);
			pStmt.setInt(3, age);
			pStmt.setBoolean(4, gender);
			pStmt.setString(5, nation);
			
			pStmt.execute();
			
			response.sendRedirect("/airticketDB/Tickets?CID=" + Integer.toString(cid));
		}
		catch(ClassNotFoundException | SQLException exc) {
			if (exc instanceof ClassNotFoundException)
				logger.log(Level.SEVERE, "Can't find JDBC driver", exc);
			else
				logger.log(Level.SEVERE, "JDBC Error");

			exc.printStackTrace();
			
			response.sendRedirect("/airticketDB/index.html");
		}
		finally
		{
			try {
			if (conn != null)
				conn.close();
			}
			catch (SQLException exc) {
				logger.log(Level.SEVERE, "Fail to close the connection");
				exc.printStackTrace();
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
