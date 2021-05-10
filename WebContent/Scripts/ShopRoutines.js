$("#shop-games").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					endpoint: "View/AJAX_Components/GameList.jsp"
				},
				beforeSend: () => {
					$("#shop-games").html("<div class=\"loader loader-lowered\">");
				},
				success: (data) => {
					setTimeout(() => {
						$("#shop-games").html(data)
					}, 250);
				},
				error: () => {
					setTimeout(() => $("#shop-games").html(setEmptyView()), 250);
				}
			}
		);
		
		$(document).ajaxComplete(
			() => {
				$(".game-add").off().click(
					function(event) {
						event.stopPropagation();
						
						updateCart(
							$(this).parent().attr("data-game-id"),
							$(this).parent().attr("data-game-name"),
							$(this).parent().attr("data-game-price")
						);
						
						$(".game-confirm").html("Aggiunto <strong>" + $(this).parent().attr("data-game-name") +"</strong> al carrello!");
						
						$(".game-confirm").show(225);
											
						setTimeout(() => $(".game-confirm").hide("slow"), 4000);
					}
				)
			}
		);
	}
);