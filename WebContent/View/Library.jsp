<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">
	<h1>
		<i class="fas fa-gamepad"></i>
		La mia collezione
	</h1>
	
	<div id="my-collection" class="games">
		<c:forEach items="${games}" var="game">
			<div data-game-id="${game.id}" style="background-image: url('Static/GamePictures/${game.image}')" class="game-container">
				<div class="game-overlay">
					${game.name}
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script src="Scripts/LibraryRoutines.js"></script>