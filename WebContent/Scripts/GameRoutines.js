$("#game-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "game",
					game_id: new URLSearchParams(window.location.search).get("game")
				},
				beforeSend: () => {
					$("#game-page").html("<div class=\"loader loader-lowered\">");
				},
				success: () => setTimeout(() => $("#game-page").load("View/Game.jsp #game-page"), 150)
			}
		);
		
		$(document).ajaxComplete(
			() => {
				$("#add-to-cart").off().click(
					() => {
						addToCartWithoutParsing(
							$(".game-flex").attr("data-game-id"),
							$(".game-flex").attr("data-game-name"),
							$(".game-flex").attr("data-game-price")
						);
					}
				)
			}
		);
	}
);