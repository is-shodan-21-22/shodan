
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
					limit: 5
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
