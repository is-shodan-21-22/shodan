$("#game-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "game",
					endpoint: "View/Game.jsp",
					game_id: new URLSearchParams(window.location.search).get("game")
				},
				beforeSend: () => {
					$("#game-page").html("<div class=\"loader loader-lowered\">");
				},
				success: (data) => {
					setTimeout(
						() => {
							$(".content").replaceWith(data.substring(0, data.lastIndexOf("\n")))
						}, 150)
				}
			}
		);
		
		$(document).ajaxComplete(
			() => {
				$("#add-to-cart").off().click(
					() => {
						updateCart(
							$(".game-flex").attr("data-game-id"),
							$(".game-flex").attr("data-game-name"),
							$(".game-flex").attr("data-game-price")
						);
											
						$("#add-to-cart").html("Aggiunto <strong>" + name +"</strong> al carrello!");
											
						setTimeout(
							() => {
								$("#add-to-cart").html("Aggiungi un'altra copia al carrello");	
							},
						4000);
					}
				)
			}
		);
	}
);