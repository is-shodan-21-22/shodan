<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">
	<h1>
		<i class="fas fa-comments-dollar"></i>
		Esplora il catalogo
	</h1>
						
	<div id="shop-games" class="games">
		<c:forEach items="${games}" var="game">
			<div data-game-id="${game.id}" style="background-image: url('Static/GamePictures/${game.image}')" class="game-container">
				<div class="game-overlay">
					${game.name}
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script src="Scripts/ShopRoutines.js"></script>