CREATE TABLE airticket(
	clientID VARCHAR(30) NOT NULL,
	airline VARCHAR(30) NOT NULL,
	departTime DATETIME NOT NULL,
	price INT NOT NULL,
	seat INT NOT NULL,
	departAirport VARCHAR(30) NOT NULL,
	arriveAirport VARCHAR(30) NOT NULL,
	arriveTime DATETIME NOT NULL,

	primary key(clientID,airline,departTime)
);
