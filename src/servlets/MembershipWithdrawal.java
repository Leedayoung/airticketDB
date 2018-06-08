package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class MembershipWithdrawal
 */
@WebServlet("/MembershipWithdrawal")
public class MembershipWithdrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembershipWithdrawal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(MembershipWithdrawal.class.getName());

		String cid_text = request.getParameter("CID");
		Integer cid = null;

		try {
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

			return;
		}
		
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/11739592_airticketDB?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "student", "student");
			
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM client WHERE CID = " + cid.toString());
			
			response.sendRedirect("/airticketDB/index.html");
		}
		catch(IllegalArgumentException exc) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", exc.getMessage());
			request.setAttribute("return_page", "/airticketDB/index.html");
			view.forward(request, response);
		}
		catch(ClassNotFoundException | SQLException exc) {
			if (exc instanceof ClassNotFoundException)
				logger.log(Level.SEVERE, "Can't find JDBC driver", exc);
			else
				logger.log(Level.SEVERE, "JDBC Error");

			exc.printStackTrace();
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", "Internal Server Error");
			request.setAttribute("return_page", "/airticketDB/index.html");
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
