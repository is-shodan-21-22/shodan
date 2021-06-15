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
				},
				error: () => {
					$(".content").html(setEmptyView("Questo gioco non esiste..."));
				}
			}
		);
	}
);

$(document).off().on("click", "#add-to-cart", () => {
	$.ajax(
		{
			type: "POST",
			url: "CartServlet",
			data: {
				action: "addGame",
				cookie: navigator.cookieEnabled,
				game_id: $(".game-info-container").parent().attr("data-game-id"),
				jsession: window.location.href.substring(
					window.location.href.indexOf("=") + 1, window.location.href.indexOf("=") + 33
					
				),
				total: $(".last-row-total").text().split(" ")[1]
			},
			success: () => {
				$("#add-to-cart").html("Gioco aggiunto al carrello!");
				updateCart();
			},
			error: () => {
				$("#add-to-cart").html("Non è stato possibile aggiungere il gioco al carrello!");	
			}	
		}
	);
});