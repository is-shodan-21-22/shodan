<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1" 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" href="Static/Assets/Icon.png" type="image/x-icon" />
		<title>Shodan - Admin</title>
		
		<!-- Admin Stylesheet -->
		<link rel="stylesheet" href="Style/Admin.css">
	</head>
	
	<body>
			<header class="shodan-admin-header">
			<h1>Benvenuto admin</h1>
			</header>
			<br>
			<div class="shodan-admin-form">
				<form enctype="multipart/form-data" action="GameServlet" method="POST">
					<h2>Aggiungi gioco</h2>
					<input id="game-name" name="game-name" type="text" placeholder="game-name" required>
					<input id="game-image" name="game-image" type="file" required>
					<input id="game-price" name="game-price" type="number" placeholder="game-price" required>
					<input type="hidden" name="action" value="addGame">
					<input type="submit" value="Aggiungi">
					<span class="admin-message">${messageGameAdd}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
				<form action="GameServlet" method="POST">
				<h2>Elimina gioco</h2>
					<input id="game-id" name="game-id" type="number" placeholder="game-id" required>
					<input type="hidden" name="action" value="deleteGame">
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageGameDelete}</span>
					<span class="error-admin-message">${errorMessageGameDelete}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
				<form action="BlogServlet" method="POST">
					<h2>Aggiungi articolo</h2>
					<input id="add-article-title" name="add-article-title" type="text" placeholder="article-title" required>
					<input id="article-shortTitle" name="article-shortTitle" type="text" placeholder="article-shortTitle" required>
					<textarea id="article-html" name="article-html" rows="4" cols="5">Inserisci il contenuto dell'articolo.</textarea>
					<input type="hidden" name="action" value="addArticle">
					<input type="submit" value="Aggiungi">
					<span class="admin-message">${messageArticleAdd}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
			<form action="BlogServlet" method="POST">
					<h2>Rimuovi articolo</h2>
					<input id="delete-article-id" name="delete-article-id" type="number" placeholder="article-id" required>
					<input type="hidden" name="action" value="deleteArticle">
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageArticleDelete}</span>
					<span class="error-admin-message">${errorMessageArticleDelete}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
				<form action="UserServlet" method="POST">
					<h2>Elimina utente</h2>
					<input id="delete-user" name="user-id" type="number" placeholder="user-id" required>
					<input type="hidden" name="action" value="removeUser">
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageUserDelete}</span>
					<span class="error-admin-message">${errorMessageUserDelete}</span>
				</form>
			</div>
			<br>
			<footer class="shodan-admin-footer">
			<h1>Shodan administration</h1>
			</footer>
	</body>
</html>