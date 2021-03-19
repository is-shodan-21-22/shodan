$("#shop-games").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "shop"
				},
				beforeSend: () => {
					$("#shop-games").html("<div class=\"loader loader-lowered\">");
				},
				success: () => setTimeout(() => $("#shop-games").load("View/Shop.jsp #shop-games"), 250)
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