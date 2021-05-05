<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" href="Static/Assets/Icon.png" type="image/x-icon" />
		<title>Shodan</title>
		
		<!-- Index Stylesheet -->
		<link rel="stylesheet" href="Style/Index.css">
		
		<!-- FontAwesome Icons -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css">
		
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<!-- JavaScript & AJAX Routines -->			
		<script src="Scripts/IndexRoutines.js"></script>
	</head>
	
	<body>
		<div class="shodan-games left">
			<div class="shodan-games-container">
				<c:forEach items="${games}" var="game">
					<div 
						data-game-id="${game.id}" 
						data-game-name="${game.name}" 
						data-game-price="${game.price}" 
						style="background-image: url('Static/GamePictures/${game.image}')" 
						class="game-container"
					>
						<div class="game-overlay">
							&nbsp;
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<main>
			<div class="shodan-logo-container">
				<video autoplay muted loop class="shodan-vhs">
					 <source src="Static/Assets/VHS.mp4" type="video/mp4">
				</video>
				
				<div class="shodan-tv">
					<div class="shodan-logo"></div>
				</div>	
			</div>
			
			<div class="shodan-info-container">
				<div class="shodan-info bordered">
					<div class="shodan-info-content">
						<i class="fas fa-chess-bishop"></i>
						<span>Old but gold</span>
						<p>
							Shodan ti permette di rivivere le emozioni dei giocatori di epoche passate: 
							decenni di industria videoludica racchiusi in un'unica, grande piattaforma.
						</p>
					</div>	
				</div>
				<div class="shodan-info bordered">
					<div class="shodan-info-content">
						<i class="fas fa-gamepad"></i>
						<span>Approfitta di offerte imperdibili</span>
						<p>
							Sconti e promozioni sono pubblicati quotidianamente, 
							tieniti aggiornato sul mondo dei videogiochi sul blog di Shodan
							e fai un salto nel passato con titoli indimenticabili!
						</p>
					</div>	
				</div>
				<div class="shodan-info">
					<div class="shodan-info-content">
						<i class="fas fa-shopping-cart"></i>
						<span>Quando compri un gioco, è tuo</span>
						<p>
							Per sempre. Puoi anche acquistare più copie dello stesso gioco, per supportare gli autori. 
							D'altronde, se i videogiochi esistono è solo grazie ai loro fan più appassionati.
						</p>
					</div>	
				</div>
			</div>
			
			<div class="shodan-form-container">
				<form onsubmit="tryLogin(); return false" method="POST">
					<h1>Accesso</h1>
		 			<div>
		 				<input id="login-username" required placeholder="Username" type="text"></input>
		 			</div>
		 			<div>
		 				<input id="login-password" required placeholder="Password" type="password"></input>
		 			</div>
		 			<div>
		 				<input id="login-submit" type="submit" name="login_submit"></input>
		 			</div>
		 			<div id="login-fail">
		 				<em>Spiacenti, il login non &egrave; andato a buon fine</em>
		 			</div>
				</form>
				<form onsubmit="trySignIn(); return false">
					<h1>Registrazione</h1>
		 			<input id="signin-username" required placeholder="Username" type="text">
		 			<input id="signin-password" required placeholder="Password" type="password">
		 			<input id="signin-password-again" required placeholder="Ripeti la password" type="password">
		 			<input id="signin-email" required placeholder="Email" type="email">
		 			<input id="signin-submit" type="submit">
		 			<div id="signin-success">
		 				<em>La registrazione &egrave; avvenuta con successo</em>
		 			</div>
				</form>
			</div>
			
			<footer>
				<div>
					<a href="https://github.com/v1enna/shodan" target="_blank">Shodan</a>
				</div>
			</footer>
		</main>
		
		<div class="shodan-games right">
			<div class="shodan-games-container">
				<c:forEach items="${desc_games}" var="desc_game">
					<div 
						data-game-id="${desc_game.id}" 
						data-game-name="${desc_game.name}" 
						data-game-price="${desc_game.price}" 
						style="background-image: url('Static/GamePictures/${desc_game.image}')" 
						class="game-container"
					>
						<div class="game-overlay">
							&nbsp;
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>