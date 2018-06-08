CREATE TABLE airticket(
	CID INT NOT NULL,
	LID INT NOT NULL,
	TDTime DATETIME NOT NULL,
	TATime DATETIME NOT NULL,
	TMileage INT NOT NULL,
	TSeat INT NOT NULL,
	PDID INT NOT NULL,
	PAID INT NOT NULL,

	primary key (CID,LID,TDTime),
	foreign key (CID) references client(CID) ON DELETE CASCADE,
	foreign key (LID) references airline(LID) ON DELETE RESTRICT,
	foreign key (PDID) references airport(PID) ON DELETE RESTRICT,
	foreign key (PAID) references airport(PID) ON DELETE RESTRICT
);

INSERT INTO airticket(CID, LID, TDTime, TATime, TMileage, TSeat, PDID, PAID)
	VALUES (1, 180, "2018-03-05 01:01:00", "2018-03-05 07:02:00", 100, 200, 1, 2),
	(1, 220, "2017-06-07 14:01:00", "2017-06-08 01:02:00", 150, 138, 2, 1);
