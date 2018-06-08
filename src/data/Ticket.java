package data;

public class Ticket {
	private String airportDName;
	private String airportAName;
	private String ticketDTime;
	private String ticketATime;
	private int ticketSeat;
	private String airlineName;
	
	public String getAirportDName() {
		return airportDName;
	}
	
	public void setAirportDName(String PDName) {
		airportDName = PDName;
	}

	public String getAirportAName() {
		return airportAName;
	}
	
	public void setAirportAName(String PAName) {
		airportAName = PAName;
	}

	public String getTicketDTime() {
		return ticketDTime;
	}
	
	public void setTicketDTime(String TDTime) {
		ticketDTime = TDTime;
	}

	public String getTicketATime() {
		return ticketATime;
	}

	public void setTicketATime(String TATime) {
		ticketATime = TATime;
	}

	public int getTicketSeat() {
		return ticketSeat;
	}
	
	public void setTicketSeat(int TSeat) {
		ticketSeat = TSeat;
	}
	
	public String getAirlineName() {
		return airlineName;
	}
	
	public void setAirlineName(String LName) {
		airlineName = LName;
	}
}	
