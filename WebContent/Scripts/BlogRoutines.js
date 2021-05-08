$(".blog").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				method: "GET",
				url: "BlogServlet",
				data: {
					action: "blog"
				},
				beforeSend: () => {
					$(".blog").html("<div class=\"loader loader-lowered\">");
				},
				success: () => {
					setTimeout(() => $(".blog").load("View/Blog.jsp .blog"), 400);
				},
				error: () => {
					setTimeout(() => $(".blog").html(setEmptyView()), 400);
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
		);
	}
);