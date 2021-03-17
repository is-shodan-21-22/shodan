USE mysql;
DROP USER IF EXISTS 'shodan_username'@'localhost';
CREATE USER 'shodan_username'@'localhost' IDENTIFIED BY 'shodan_password';
DROP DATABASE IF EXISTS shodan;
CREATE DATABASE shodan;
GRANT ALL ON shodan.* TO 'shodan_username'@'localhost';
SET SQL_SAFE_UPDATES = 0;
USE shodan;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
	user_id int NOT NULL AUTO_INCREMENT,
	user_name varchar(16) NOT NULL,
    user_password varchar(16) NOT NULL,
    user_avatar varchar(16),
    user_email varchar(16),
    user_admin boolean DEFAULT false,
    
    PRIMARY KEY(user_id)
);

DROP TABLE IF EXISTS games;
CREATE TABLE games(
	game_id int NOT NULL AUTO_INCREMENT,
	game_name varchar(32) NOT NULL,
    game_image varchar(32) NOT NULL,
    game_description varchar(256),
    game_price int DEFAULT 0,
    game_release date,
    
    PRIMARY KEY(game_id)
);

DROP TABLE IF EXISTS has_game;
CREATE TABLE has_game(
	user_id int NOT NULL AUTO_INCREMENT,
	game_id int NOT NULL,
    
    PRIMARY KEY(user_id, game_id)
);

DROP TABLE IF EXISTS blog;
CREATE TABLE blog(
	blog_id int NOT NULL AUTO_INCREMENT,
    blog_title varchar(128) NOT NULL,
    blog_short_title varchar(256),
    blog_html varchar(512),
    
    PRIMARY KEY(blog_id)
);