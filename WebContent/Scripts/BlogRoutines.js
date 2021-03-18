$(document).ready(
	() => {
		$.ajax(
			{
				method: "GET",
				url: "BlogServlet",
				data: {
					action: "blog"
				},
				success: () => $(".blog").css("style", "none")
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
		);
	}
);