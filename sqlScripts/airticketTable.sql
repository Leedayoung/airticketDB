CREATE TABLE airticket(
	CID INT NOT NULL references client(CID),
	LID INT NOT NULL references airline(LID),
	TDTime DATETIME NOT NULL,
	TATime DATETIME NOT NULL,
	TPrice INT NOT NULL,
	TSeat INT NOT NULL,
	PDID INT NOT NULL references airport(PID),
	PAID INT NOT NULL references airport(PID),

	primary key(CID,LID,TDTime)
);

INSERT INTO airticket(CID, LID, TDTime, TATime, TPrice, TSeat, PDID, PAID)
	VALUES(1, 180, "2018-03-05 01:01:00", "2018-03-05 07:02:00", 100, 200, 1, 2),
	(1, 180, "2015-06-07 14:01:01", "2017-06-08 01:02:33", 150, 138, 2, 1);
