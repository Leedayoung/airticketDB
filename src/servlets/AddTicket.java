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
 * Servlet implementation class AddTicket
 */
@WebServlet("/AddTicket")
public class AddTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(AddTicket.class.getName());

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
			
			String PDCity = request.getParameter("PDCity");
			String PACity = request.getParameter("PACity");

			if (PDCity == null)
				throw new IllegalArgumentException("Please enter the departure city");
			else if (PACity == null)
				throw new IllegalArgumentException("Please enter the arrival city");
			
			String TDDate_text = request.getParameter("TDDate");
			String TDTime_text = request.getParameter("TDTime");	
			String TADate_text = request.getParameter("TADate");
			String TATime_text = request.getParameter("TATime");
			
			if (TDDate_text == null || TDTime_text == null || TADate_text == null || TATime_text == null)
				throw new IllegalArgumentException("Please enter the dates and times");
			
			Timestamp TDTime;
			Timestamp TATime;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				TDTime = new Timestamp(formatter.parse(TDDate_text + " " + TDTime_text + ":00").getTime());
				TATime = new Timestamp(formatter.parse(TADate_text + " " + TATime_text + ":00").getTime());
			}
			catch (ParseException exc){
				throw new IllegalArgumentException("Illegal Date Format (yyyy.MM.dd hh:mm)");
			}

			String LName = request.getParameter("LName");
			if (LName == null)
				throw new IllegalArgumentException("Please enter the name of airline");
			
			String TSeat_text = request.getParameter("TSeat");
			int TSeat;
			
			if (TSeat_text == null)
				throw new IllegalArgumentException("Please enter the seat number");
			try {
				TSeat = Integer.valueOf(TSeat_text);
			}
			catch (NumberFormatException exc){
				throw new IllegalArgumentException("Illegal seat number");
			}

			String TMileage_text = request.getParameter("TMileage");
			int TMileage;
			
			if (TMileage_text == null)
				throw new IllegalArgumentException("Please enter the mileage of the ticket");
			try {
				TMileage = Integer.valueOf(TMileage_text);
			}
			catch (NumberFormatException exc) {
				throw new IllegalArgumentException("Illegal mileage");
			}
			
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO airticket(CID, LID, TDTime, TATime, TMileage, TSeat, PDID, PAID) "
					+ "SELECT ?, airline.LID, ?, ?, ?, ?, PD.PID, PA.PID "
					+ "FROM airline, airport PD, airport PA "
					+ "WHERE airline.LName = ? "
					+ "AND PD.PCity = ? "
					+ "AND PA.PCity = ?");
			pStmt.setInt(1, cid);
			pStmt.setTimestamp(2, TDTime);
			pStmt.setTimestamp(3, TATime);
			pStmt.setInt(4, TMileage);
			pStmt.setInt(5, TSeat);
			pStmt.setString(6, LName);
			pStmt.setString(7, PDCity);
			pStmt.setString(8, PACity);
			
			if (pStmt.executeUpdate() == 0)
				throw new IllegalArgumentException("Illegal parameters. Please checks a airport name or airline name.");
			
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
