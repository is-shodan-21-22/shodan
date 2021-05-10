<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

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