Select CGender, count(CID) from client where CID IN
	(Select CID from airticket where PDID IN
		(Select PID from airport 
			where PName = 'Incheon International Airport')
	)GROUP BY CGender;

Select CGender, count(DISTINCT client.CID) from airticket, airport, client where
	airticket.PDID = airport.PID AND airticket.CID = client.CID
	AND airport.PName = 'Incheon International Airport'
	GROUP BY CGender;
