use final_project_color_game;

# = Select biggest pid
select * from player order by PID desc limit 1;

# - Select top 10 players
select * from player order by Pscore desc limit 10;

# - select top 10 teams
select * from team where TID >1 order by Tscore desc limit 10;

# -	Select concurrent leagues
select * from league order by sdate desc limit 2; # - for team leagues

# - select find team by name
select * from team where Tname = 'Bir Zeit';

# select find player by name;
select * from player where 'JustinBieber' like (select CONCAT(Fname, Lname, Mname) AS ConcatenatedString) or 'JustinBieber' like (select CONCAT(Fname, Lname) AS ConcatenatedString);

# - insert palyer 
INSERT INTO player (Fname,Lname,Mname,Pscore,TID) VALUES ('yoni','BBB',null,100,1); # - (PID,first name,last name,middle name,score,team)

# - insert team
INSERT INTO team (Tname,Tscore) VALUES ('No shirts',0); # - (TID,team name,team score)

# - insert league
INSERT INTO league VALUES (1,'2021-10-01 00:00:00','2021-10-31 23:59:59');

# - insert Sleague - needs a 'for' loop
insert into singleleague values (1);

# - insert Tleague - needs a 'for' loop
insert into teamleague values (1,2);

# - insert scompetes
insert into scompetes values (1,1);

# - insert tcompetes
insert into tcompetes values (1,2);

# - update team score 
UPDATE team set Tscore = (Select avg(Pscore) from player where TID=8) WHERE TID = 8; # - replace TID with the relevant team

# - update player score
UPDATE player set Pscore = 100 where PID=4; # - instead of '100' put here the score he got in the game

# - update player team
update player set TID = 8 where PID=4; # - instead 1, put here the relevant number of the team

# - update Tleague MVP 
update teamleague set MVP_ID = (select PID from player order by Pscore desc limit 1) where LID = (select LID from league order by sdate desc limit 1);

# - delete player 
DELETE FROM player WHERE PID = 27; # - deletes the player with the right PID, effects all the referencing tables

# - delete team
delete from team where TID = 3; # - # - deletes the team with the right PID, effects all the referencing tables