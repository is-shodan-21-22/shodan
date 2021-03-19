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
			
			<p>				
				<a href="">
					Vai alla pagina di System Shock nel negozio!&nbsp;
					<i class="fas fa-arrow-alt-circle-right"></i>
				</a>
			</p>
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
				
	<div class="games">
		<c:forEach items="${games}" var="game">
			<div data-game-id="${game.id}" style="background-image: url('static/games/${game.image})" class="game-container">
				<div class="overlay">
					${game.title}
				</div>
			</div>
		</c:forEach>
	</div>
			
	<!-- Ultimi 3 articoli dal blog -->
	<h1>
		<i class="fas fa-comment-dots"></i>
		Notizie da Shodan
	</h1>
			
	<div class="blog">
		<c:forEach items="${articles}" var="article">
			<div class="article-container">
				<div class="article">
					<h1> ${article.title} </h1>
					<div> ${article.shortTitle} </div>
					<span id="blog-link" class='blog-link' data-blog-id="${article.id}">
						<i class="fas fa-caret-square-right"></i> Leggi la notizia
					</span>
				</div>
			 </div>
		</c:forEach>
	</div>
	
</div>

<script src="Scripts/DashboardRoutines.js"></script>