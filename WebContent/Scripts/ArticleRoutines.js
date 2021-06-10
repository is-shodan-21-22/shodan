$("#blog-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "BlogServlet",
				data: {
					action: "article",
					blog_id: new URLSearchParams(window.location.search).get("blog"),
					endpoint: "View/Article.jsp"
				},
				beforeSend: () => {
					$("#blog-page").html("<div class=\"loader loader-lowered\">");
				},
				success: (data) => { 
					setTimeout(
						() => { 
							$(".content").replaceWith(data.substring(0, data.lastIndexOf("\n")))
						}, 
					150)
				}
			}
		);
	}
);