package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTicket
 */
@WebServlet("/DeleteTicket")
public class DeleteTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(DeleteTicket.class.getName());

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
			
			String LName = request.getParameter("LName");

			if (LName == null)
				throw new IllegalArgumentException("Please enter the airline name");
			
			String TDDate_text = request.getParameter("TDDate");
			String TDTime_text = request.getParameter("TDTime");	
			
			if (TDDate_text == null || TDTime_text == null)
				throw new IllegalArgumentException("Please enter the dates and times");
			
			Timestamp TDTime;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				TDTime = new Timestamp(formatter.parse(TDDate_text + " " + TDTime_text + ":00").getTime());
			}
			catch (ParseException exc){
				throw new IllegalArgumentException("Illegal Date Format (yyyy.MM.dd hh:mm)");
			}

			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM airticket "
					+ "WHERE CID = ? and TDTime = ? "
					+ "and LID IN (Select LID FROM airline WHERE LName = ?)");
			pStmt.setInt(1, cid);
			pStmt.setTimestamp(2, TDTime);
			pStmt.setString(3, LName);
			
			if (pStmt.executeUpdate() == 0)
				throw new IllegalArgumentException("No such ticket. Please check the airline name");
			
			response.sendRedirect("/airticketDB/TicketTable?CID=" + cid.toString());
		}
		catch(IllegalArgumentException exc) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error.jsp");
			request.setAttribute("msg", exc.getMessage());
			request.setAttribute("return_page", "/airticketDB/TicketTable?CID=" + cid.toString());
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
