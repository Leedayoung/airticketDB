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
