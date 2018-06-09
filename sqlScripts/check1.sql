Select CName from client where CID IN
(Select A.CID from (
Select * from airticket where LID IN
( Select LID from airline where LUnion = 'SKYTEAM')
) A GROUP BY CID having sum(TMileage) >=150
);

Select CName from airline, airticket, client where
airline.LUnion = 'SKYTEAM' AND airticket.LID = airline.LID 
AND airticket.CID = client.CID 
GROUP BY client.CID having sum(TMileage) >= 150;

