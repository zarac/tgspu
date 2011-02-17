/*
 * The database skeleton for our l33t Black Jack game.
 */

-- The database containing black jack information.
CREATE DATABASE "BlackJack";

-- USE it so we insert stuff in the right place.
-- TODO: ?? doens't work?!
USE "BlackJack";

-- "Users" contains the users and their stack (amount of credits)
CREATE TABLE Users (
    ID int,
    Name varchar,
    Stack int
);

-- Some random initial entries in the database.
INSERT INTO Users VALUES(0, "Rolf", 1000);
INSERT INTO Users VALUES(1, "Hannes", 10000);
INSERT INTO Users VALUES(2, "Simon", 5000);
INSERT INTO Users VALUES(3, "Linus", 3000);
INSERT INTO Users VALUES(4, "Gustav", 4000);
INSERT INTO Users VALUES(5, "Anders", 3000);
INSERT INTO Users VALUES(6, "Markus", 2000);
