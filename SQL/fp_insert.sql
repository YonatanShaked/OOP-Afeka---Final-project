use final_project_color_game;
#select * from team;

INSERT INTO team VALUES
(1,'NO_TEAM',0),
(2,'Bir Zeit',0),
(3,'TAU',0),
(4,'AFEKA_BOYS',0),
(5,'wish we were in the technion',0),
(6,'A.Zensors fan boys',0),
(7,'olga PTSD club',0);

# - update and calculate team score
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=4) WHERE TID = 4;



# - insert the players to group
INSERT INTO player VALUES
(1,'Emalik','Malik',null,150,1),
(2,'Vladimir','Dont Putin',null,170,1),
(3,'Oren','Hazan','Casino',1350,1),
(4,'Drop','Table',null,110,1),
(5,'Ahmad','Ahmad','Ahmad',1950,1),
(6,'Justin','Bieber',null,900,1),
(7,'oren','shlomo',null,150,2),
(8,'vladi','Dont touch',null,170,2),
(9,'Oren','Hagag',null,1350,3),
(10,'Drop','Schema',null,110,3),
(11,'Ahmad','Muhamad','yasin',1950,4),
(12,'Justin','Bieber','muhamad',900,4),
(13,'Emalik','berkovich',null,150,5),
(14,'ester','russian',null,170,5),
(15,'beer','sheva','Casino',1350,6),
(16,'gal','druker',null,110,6),
(17,'Ahmad','Emalik','null',1950,7),
(18,'Justin','select',null,900,7),
(19,'yes','we','cant',150,2),
(20,'one','two','three',170,3),
(21,'Dganit','Agmon','2',1350,4),
(22,'Ornit','Armon',null,110,5),
(23,'shimon','Ahmad','Ahmad',1950,6),
(24,'shimi','Bieber','barbershop',900,7)
;

UPDATE team set Tscore = (Select avg(Pscore) from player where TID=2) WHERE TID = 2;
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=3) WHERE TID = 3;
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=4) WHERE TID = 4;
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=5) WHERE TID = 5;
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=6) WHERE TID = 6;
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=7) WHERE TID = 7;

select * from player;


select * from team;


# - delete a player
DELETE FROM player WHERE PID = 21;

INSERT INTO league VALUES
(1,'2021-10-01 00:00:00','2021-10-31 23:59:59'),
(2,'2021-10-01 00:00:00','2021-10-31 23:59:59'),
(3,'2021-11-01 00:00:00','2021-11-30 23:59:59'),
(4,'2021-11-01 00:00:00','2021-11-30 23:59:59'),
(5,'2021-12-01 00:00:00','2021-12-31 23:59:59'),
(6,'2021-12-01 00:00:00','2021-12-31 23:59:59'),
(7,'2022-01-01 00:00:00','2022-01-31 23:59:59'),
(8,'2022-01-01 00:00:00','2022-01-31 23:59:59');

insert into singleleague values 
(1),(3),(5),(7);

# - insert into scompetes values 

select * from scompetes;


drop procedure if exists doWhile;
DELIMITER //  
CREATE PROCEDURE doWhile()   
BEGIN
DECLARE i INT DEFAULT 1; 
WHILE (i < 24) DO
	insert into tcompetes values 
		(i,2),
		(i,4),
		(i,6),
		(i,8);
    INSERT INTO `mytable` (code, active, total) values (i, 1, 1);
    SET i = i+1;
END WHILE;
END;
//  
CALL doWhile();



select * from tcompetes;

insert into teamleague values 
((select PID from player order by Pscore desc limit 1),2),
((select PID from player order by Pscore desc limit 1),4),
((select PID from player order by Pscore desc limit 1),6),
((select PID from player order by Pscore desc limit 1),8);
select * from player order by Pscore;

select * from league;
select * from teamleague;
select * from team;
select * from player;
select * from singleleague;
select * from scompetes;
select * from tcompetes;