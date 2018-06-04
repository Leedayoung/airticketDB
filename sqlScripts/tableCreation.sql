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

CREATE TABLE airline(
	name VARCHAR(30) NOT NULL primary key,
	airUnion VARCHAR(30) NOT NULL,
	nationaility VARCHAR(30) NOT NULL
);

CREATE TABLE airport(
	name VARCHAR(30) NOT NULL primary key,
	city VARCHAR(30) NOT NULL
);

CREATE TABLE client(
	clientID VARCHAR(30) NOT NULL primary key,
	name VARCHAR(30) NOT NULL,
	age INT NOT NULL,
	gender BOOLEAN NOT NULL,
	nationality VARCHAR(30) NOT NULL
);
