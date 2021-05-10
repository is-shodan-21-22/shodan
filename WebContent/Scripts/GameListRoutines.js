$(function() {
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
												
			setTimeout(
				() => $(".game-confirm").hide("slow"), 
			4000);
		}
	);
});