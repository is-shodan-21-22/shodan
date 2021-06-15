$(function() {
	$(".blog-link").click(
		function() {
			window.history.pushState(null, null, "?blog=" + $(this).attr("data-blog-id"));
			$("#app").load("View/Article.jsp");
			
			if(navigator.cookieEnabled)
				localStorage.setItem("last-page", "Article");
		}
	);
});