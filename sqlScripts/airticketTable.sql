CREATE TABLE airticket(
	CID INT NOT NULL,
	LID VARCHAR(30) NOT NULL,
	TDTime DATETIME NOT NULL,
	TATime DATETIME NOT NULL,
	TPrice INT NOT NULL,
	TSeat INT NOT NULL,
	PDID INT NOT NULL,
	PAID INT NOT NULL,

	primary key(CID,LID,TDTime)
);
