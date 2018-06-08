

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
 * Servlet implementation class Tickets
 */
@WebServlet("/Tickets")
public class Tickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(Tickets.class.getName());

		String cid_text = request.getParameter("CID");
		Integer cid = null;
		
		try {
			if (cid_text == null)
				throw new IllegalArgumentException("Illegal CID");
			cid = Integer.valueOf(cid_text);
		}
		catch (IllegalArgumentException exc){
			response.sendRedirect("/airticketDB/index.html");
			return;
		}

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/11739592_airticketDB?serverTimezone=UTC&useSSL=false", "student", "student");
			
			Statement Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery("SELECT CName FROM client WHERE client.CID = " + cid.toString());
			String name = null;
			
			if (rs.next())
				name = rs.getString(1);

			if (name == null)
				throw new IllegalArgumentException("Illegal CID");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/tickets.jsp");
			request.setAttribute("CName", name);

			view.forward(request, response);
		}
		catch(IllegalArgumentException exc) {
			response.sendRedirect("/airticketDB/index.html");
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
