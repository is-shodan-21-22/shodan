$(".blog").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				method: "POST",
				url: "latestGamesServlet",
				success: (data) => {
						$("#latest-games").html(data);
				}
			}			
		).done(
			() => {
				$(".game-container").click(
					function() {
						window.history.pushState(null, null, "?game=" + $(this).attr("data-game-id"));
						$("#app").load("View/Game.jsp");
					}
				);
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
		)
		
		$(document).ajaxComplete(
			() => {
				console.log($("#blog-link").val());
				$(".blog-link").click(
					function() {
						console.log("click");
						window.history.pushState(null, null, "?blog=" + $(this).attr("data-blog-id"));
						$("#app").load("View/Article.jsp");
					}
				);
			}
		);
		
	}
	
);
