package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet("/UpdateUserInfo")
public class UpdateUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(Signup.class.getName());

		Integer cid = null;

		try {
			String cid_text =request.getParameter("CID");

			if (cid_text == null)
				throw new IllegalArgumentException ("Illegal CID");
		
			try {
				cid = Integer.valueOf(cid_text);
			}
			catch(NumberFormatException exc) {
				throw new IllegalArgumentException ("Illegal CID");
			}
		}
		catch (IllegalArgumentException exc) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", exc.getMessage());
			request.setAttribute("return_page", "/airticketDB/index.html");
			view.forward(request, response);
		}

		Connection conn = null;

		try {
			String name = request.getParameter("CName");
			if (name == null)
				throw new IllegalArgumentException("Illegal Name");
			
			String age_text = request.getParameter("CAge");
			int age;
			try {
				if (age_text == null)
					throw new IllegalArgumentException("Illegal Age");

				age = Integer.valueOf(age_text);
			}
			catch (NumberFormatException exc) {
				throw new IllegalArgumentException("Illegal Age");
			}

			String gender_text = request.getParameter("CGender");
			boolean gender;
			if (gender_text == null)
				throw new IllegalArgumentException("Illegal Gender");

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
			
			String nation = request.getParameter("CNation");
			if (nation == null)
				throw new IllegalArgumentException("Illegal Nationality");
		
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/11739592_airticketDB?serverTimezone=UTC&useSSL=false", "student", "student");
			
			PreparedStatement pStmt = conn.prepareStatement("UPDATE client "
					+ "SET CName = ?, CAge = ?, CGender = ?, CNation = ? "
					+ "WHERE CID = ?");
			pStmt.setString(1,  name);
			pStmt.setInt(2, age);
			pStmt.setBoolean(3, gender);
			pStmt.setString(4, nation);
			pStmt.setInt(5, cid);
			
			if(pStmt.executeUpdate() == 0)
				throw new IllegalArgumentException("No such CID");
			
			response.sendRedirect("/airticketDB/TicketTable?CID=" + Integer.toString(cid));
		}
		catch (IllegalArgumentException exc) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", exc.getMessage());
			request.setAttribute("return_page", "/airticketDB/TicketTable?CID=" + String.valueOf(cid));
			view.forward(request, response);
			return;
		}
		catch(ClassNotFoundException | SQLException exc) {
			if (exc instanceof ClassNotFoundException)
				logger.log(Level.SEVERE, "Can't find JDBC driver", exc);
			else
				logger.log(Level.SEVERE, "JDBC Error");

			exc.printStackTrace();
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", "Internal Server Error");
			request.setAttribute("return_page", "/airticketDB/TicketTable?CID=" + cid.toString());
			view.forward(request, response);
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
