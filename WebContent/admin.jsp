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
		<div>
			<h1>Benvenuto amministratore</h1>
			<div class="shodan-admin-form">
				<form action="GameServlet" method="POST">
					<h1>Aggiungi gioco</h1>
					<input id="game-name" name="game-name" type="text" placeholder="game-name" required>
					<input id="game-image" name="game-image" type="text" placeholder="game-image.png" required>
					<input id="game-price" name="game-price" type="number" placeholder="game-price" required>
					<input type="hidden" id="action" name="action" value="addGame">
					<input type="submit" value="Aggiungi">
					<span class="admin-message">${messageGameAdd}</span>
				</form>
				<form action="GameServlet" method="POST">
					<h1>Elimina gioco</h1>
					<input id="game-id" name="game-id" type="number" placeholder="game-id" required>
					<input type="hidden" id="action" name="action" value="deleteGame">
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageGameDelete}</span>
					<span class="error-admin-message">${errorMessageGameDelete}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
				<form action="BlogServlet" method="POST">
					<h1>Aggiungi articolo</h1>
					<input id="add-article-title" name="add-article-title" type="text" placeholder="article-title" required>
					<input id="article-shortTitle" name="article-shortTitle" type="text" placeholder="article-shortTitle" required>
					<textarea id="article-html" name="article-html" rows="4" cols="5">Inserisci il contenuto dell'articolo.</textarea>
					<input type="hidden" id="action" name="action" value="addArticle">
					<input type="submit" value="Aggiungi">
					<span class="admin-message">${messageArticleAdd}</span>
				</form>
				<form action="BlogServlet" method="POST">
					<h1>Rimuovi articolo</h1>
					<input id="delete-article-id" name="delete-article-id" type="number" placeholder="article-id" required>
					<input type="hidden" id="action" name="action" value="deleteArticle">
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageArticleDelete}</span>
					<span class="error-admin-message">${errorMessageArticleDelete}</span>
				</form>
			</div>
			<br>
			<div class="shodan-admin-form">
				<form action="UserServlet" method="POST">
					<h1>Elimina utente</h1>
					<input id="delete-user" name="user-id" type="number" placeholder="user-id" required>
					<input type="submit" value="Elimina">
					<span class="admin-message">${messageUserDelete}</span>
					<span class="error-admin-message">${errorMessageUserDelete}</span>
				</form>
			</div>
		</div>
	</body>
</html>