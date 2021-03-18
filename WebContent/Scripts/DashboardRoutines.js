	
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
					method: "POST",
					url: "latestBlogsServlet",
					success: (data) => {
						$("#latest-blogs").html(data);
					}
				}			
			).done(
				() => {
					$(".blog-link").click(
						function() {
							window.history.pushState(null, null, "?blog=" + $(this).attr("data-blog-id"));
							$("#app").load("View/Article.jsp");
						}
					);
				}
			)
		}
	);