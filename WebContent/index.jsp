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
			<!-- View/AJAX_Components/GameSlideshow.jsp -->
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
						<span>Nessun gioco è mai troppo datato</span>
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
						<span>Supporta direttamente gli autori</span>
						<p>
							La maggior parte del ricavato raggiunge direttamente le aziende, per supportare le case di sviluppo.
							D'altronde, se i videogiochi esistono è solo grazie ai loro fan più appassionati.
						</p>
					</div>	
				</div>
			</div>
			
			<div class="shodan-form-container">
				<form onsubmit="tryLogin(); return false">
					<h1>Accesso</h1>
					
		 			<input id="login-username" required placeholder="Username" type="text">
		 			<input id="login-password" required placeholder="Password" type="password">
		 			<input id="login-submit" type="submit">
		 			
		 			<div id="login-message"></div>
				</form>
				
				<form onsubmit="trySignIn(); return false">
					<h1>Registrazione</h1>
					
		 			<input id="signin-username" required placeholder="Username" type="text">
		 			<input id="signin-password" required placeholder="Password" type="password">
		 			<input id="signin-password-again" required placeholder="Ripeti la password" type="password">
		 			<input id="signin-email" required placeholder="Email" type="email">
		 			<input id="signin-submit" type="submit">
		 			
		 			<div id="signin-message"></div>
				</form>
			</div>
			
			<footer>
				<div>
					<a href="https://github.com/v1enna/shodan" target="_blank">Shodan</a>
				</div>
			</footer>
		</main>
		
		<div class="shodan-games right">
			<!-- View/AJAX_Components/GameSlideshow.jsp -->
		</div>
	</body>
</html>