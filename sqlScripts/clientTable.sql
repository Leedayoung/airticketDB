CREATE TABLE client(
	CID INT NOT NULL primary key,
	CName VARCHAR(30) NOT NULL,
	CAge INT NOT NULL,
	CGender BOOLEAN NOT NULL,
	CNation VARCHAR(30) NOT NULL
);

INSERT INTO client(CID, CName, CAge, CGender, CNation)
	VALUES (1, "Dayeong", 24, TRUE, "Korean"),
	(2, "Hojun", 24, FALSE, "Korean"),
	(3, "Danniel", 25, FALSE, "British")