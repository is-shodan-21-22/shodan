<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Videogioco in evidenza -->
<div class="featured">
	<div class="featured-text-outer">
		<div class="featured-text-inner">
			<p>
				<strong>
					«Look at you, hacker: a pathetic creature of meat and bone,<br />
					panting and sweating as you run through my corridors»
				</strong>
			</p>
								
			<p>
				System Shock, cult del 1994, è finalmente disponibile!<br />
				Sei pronto a combattere cyborg, mutanti e IA impazzite?
			</p>
					
			<div class="featured-button button button--submit button--unshadow">
				Vai alla pagina di System Shock nel negozio&nbsp;
				<i class="fas fa-arrow-alt-circle-right"></i>
			</div>
		</div>
	</div>
</div>

<!-- Contenuto principale della dashboard -->
<div class="content">

	<!-- Ultimi 5 giochi rilasciati -->
	<h1>
		<i class="fas fa-dice"></i>
		Ultime uscite
	</h1>	
	
	<div class="game-confirm">
		<i class="fas fa-comments-dollar"></i>&nbsp;
		<span></span>
	</div>
				
	<div class="games">
		<!-- View/AJAX_Components/GameList.jsp -->
	</div>
			
	<!-- Ultimi 3 articoli dal blog -->
	<h1>
		<i class="fas fa-comment-dots"></i>
		Notizie da Shodan
	</h1>
			
	<div class="blog">
		<!-- View/AJAX_Components/BlogList.jsp -->
	</div>
	
</div>

<script src="Scripts/DashboardRoutines.js"></script>