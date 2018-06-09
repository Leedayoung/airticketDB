CREATE TABLE airline(
	LID INT NOT NULL primary key,
	LName VARCHAR(30) NOT NULL,
	LAlliance VARCHAR(30) NOT NULL,
	LNation VARCHAR(30) NOT NULL
);

INSERT INTO airline(LID, LName, LAlliance, LNation)
	VALUES (180, "Korean Air", "SKYTEAM", "Korea"),
	(220, "Lufthansa", "STAR ALLIANCE", "Germany"),
	(555, "Aeroflot", "SKYTEAM", "Russia"),
	(257, "Austrian Airlines", "STAR ALLIANCE", "Austria"),
	(125, "British Airways", "ONEWORLD", "Britain")
