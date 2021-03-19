$("#blog-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "BlogServlet",
				data: {
					action: "article",
					blog_id: new URLSearchParams(window.location.search).get("blog")
				},
				beforeSend: () => {
					$("#blog-page").html("<div class=\"loader loader-lowered\">");
				},
				success: () => setTimeout(() => $("#blog-page").load("View/Article.jsp #blog-page"), 150)
			}
		);
	}
);