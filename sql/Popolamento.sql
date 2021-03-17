USE shodan;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM users;
INSERT INTO users(user_name, user_password) VALUES 
	('admin', '123');

DELETE FROM games;
INSERT INTO games(game_name, game_image) VALUES 
	('Doom', 'Doom.png'),
    ('Half Life 2', 'HalfLife2.jpg'),
    ('Crash Bandicoot', 'Crash.jpg'),
    ('Red Alert', 'RedAlert.jpg'),
    ('Final Fantasy VII', 'FF7.jpg');

DELETE FROM has_game;
INSERT INTO has_game VALUES 
	(1, 1),
    (1, 2);

DELETE FROM blog;
INSERT INTO blog(blog_title, blog_short_title, blog_html) VALUES
	("Il mio articolo", "Una breve descrizione", "Il contenuto dell'articolo"),
    ("Un altro articolo", "Una lunga lunghissima proprio lunga descrizione", "Un testo");

SELECT * FROM users;
SELECT * FROM has_game;