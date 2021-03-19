import { updateCart } from "./CartRoutines.js";

$(".blog").html("");
$(".games").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				method: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					limit: 5
				},
				beforeSend: () => {
					$(".games").html("<div class=\"loader\">");
				},
				success: () => {
					setTimeout(() => {
						$(".games").load("View/Dashboard.jsp .game-container");
					}, 400)
				}
			}
		);
		
		$.ajax(
			{
				method: "GET",
				url: "BlogServlet",
				data: {
					action: "blog",
					limit: 3
				},
				beforeSend: () => {
					$(".blog").html("<div class=\"loader\">");
				},
				success: () => {
					setTimeout(() => {
						$(".blog").load("View/Dashboard.jsp .article-container");
					}, 400)
				}
			}			
		);
		
		$(document).ajaxComplete(
			() => {
				$(".blog-link").click(
					function() {
						window.history.pushState(null, null, "?blog=" + $(this).attr("data-blog-id"));
						$("#app").load("View/Article.jsp");
					}
				);
				
				$(".game-container").click(
					function() {
						window.history.pushState(null, null, "?game=" + $(this).attr("data-game-id"));
						$("#app").load("View/Game.jsp");
					}
				);
				
				$(".game-add").off().click(
					function(event) {
						event.stopPropagation();

						const cart = JSON.parse(localStorage.getItem("cart"));
						let newCart = cart == null ? [] : cart;
						
						newCart[cart == null ? 0 : cart.length] = {
							game_id: $(this).parent().attr("data-game-id"),
							game_name: $(this).parent().attr("data-game-name"),
							game_price: $(this).parent().attr("data-game-price")
						};

						console.log(newCart);
						
						$(".game-confirm>span").html("Aggiunto <strong>" + $(this).parent().attr("data-game-name") +"</strong> al carrello!");
						$(".game-confirm").show(225);
						
						setTimeout(() => $(".game-confirm").hide("slow"), 4000);
						
						localStorage.setItem("cart", JSON.stringify(newCart));
						
						updateCart();
						
						return false;
					}
				)
			}
		);
		
	}
	
);
