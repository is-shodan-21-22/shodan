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
		
		<script>
			function tryRedirect() {
				let parsed_path;
				if(!navigator.cookieEnabled)
					if(window.location.href.indexOf(";") != -1)
						parsed_path = "app.jsp" + window.location.href.substring(window.location.href.indexOf(";"));
					else {
						window.history.back();
						return;	
					}
				else
					parsed_path = "app.jsp";
				console.log("Redirecting to " + parsed_path)
				window.location.replace(parsed_path);
			}
		</script>
	</head>
	
	<body>
			<header class="shodan-admin-header"></header>
			
			<div onclick="tryRedirect();" id="go-back">
				Torna indietro
			</div>
			
			<div class="shodan-admin-form">
				<form enctype="multipart/form-data" action="GameServlet" method="POST">
					<h2>Aggiungi gioco</h2>
					<input class="input" name="game-name" type="text" placeholder="Inserisci il nome del gioco" required>
					<input class="input" name="game-price" type="number" placeholder="Inserisci il prezzo del gioco" required>
					<input class="input" name="game-date" type="date" required>
					<textarea class="input" name="game-description" rows="4" cols="5">Inserisci una descrizione del gioco</textarea>
					<input class="input" name="game-image" type="file" required>
					<input type="hidden" name="action" value="addGame">
					<input class="button" type="submit" value="Aggiungi">
					<span class="admin-message">${messageGameAdd}</span>
					<span class="error-admin-message">${errorMessageGameAdd}</span>
				</form>
				
				<form action="BlogServlet" method="POST">
					<h2>Aggiungi articolo</h2>
					<input class="input" name="add-article-title" type="text" placeholder="Inserisci il titolo dell'articolo" required>
					<input class="input" name="article-shortTitle" type="text" placeholder="Inserisci il sottotitolo dell'articolo" required>
					<textarea class="input" name="article-html" rows="4" cols="5">Inserisci il contenuto dell'articolo.</textarea>
					<input type="hidden" name="action" value="addArticle">
					<input class="button" type="submit" value="Aggiungi">
					<span class="admin-message">${messageArticleAdd}</span>
				</form>
				
			</div>
			
			<div class="shodan-admin-form">
				<form action="GameServlet" method="POST" >
					<h2>Elimina gioco</h2>
					<input class="input" name="game-id" type="number" placeholder="Inserisci l'ID del gioco" required>
					<input type="hidden" name="action" value="deleteGame">
					<input class="button" type="submit" value="Elimina">
					<span class="admin-message">${messageGameDelete}</span>
					<span class="error-admin-message">${errorMessageGameDelete}</span>
				</form>
				
				<form action="BlogServlet" method="POST">
					<h2>Rimuovi articolo</h2>
					<input class="input" name="delete-article-id" type="number" placeholder="Inserisci l'ID dell'articolo" required>
					<input type="hidden" name="action" value="deleteArticle">
					<input class="button" type="submit" value="Elimina">
					<span class="admin-message">${messageArticleDelete}</span>
					<span class="error-admin-message">${errorMessageArticleDelete}</span>
				</form>
			</div>
			
			<div class="shodan-admin-form">
				<form action="UserServlet" method="POST">
					<h2>Elimina utente</h2>
					<input class="input" name="user-id" type="number" placeholder="Inserisci l'ID dell'utente" required>
					<input type="hidden" name="action" value="removeUser">
					<input class="button" type="submit" value="Elimina">
					<span class="admin-message">${messageUserDelete}</span>
					<span class="error-admin-message">${errorMessageUserDelete}</span>
				</form>
			</div>
	</body>
</html>