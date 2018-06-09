Select CName from client where CID IN
( Select CID from airticket where LID IN
( Select LID from airline where LName = 'Korean Air')
AND TMileage =150);

Select CName from airline, airticket, client where
client.CID = airticket.CID AND airticket.LID = airline.LID 
AND airline.LName = 'Korean Air' AND airticket.TMileage = 150;


