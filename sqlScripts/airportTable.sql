CREATE TABLE airport(
	PID INT NOT NULL primary key,
	PName VARCHAR(50) NOT NULL,
	PCity VARCHAR(20) NOT NULL
);

INSERT INTO airport(PID, PName, PCity)
	VALUES (1, "Incheon International Airport", "Incheon"),
	(2, "Vienna international Airport", "Vienna"),
	(3, "London Heathrow Airport", "London"),
	(4, "Domodedovo International Airport", "Moscow")