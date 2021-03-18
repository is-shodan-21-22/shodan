if(!localStorage.hasOwnProperty("uid"))
	window.location.replace('index.jsp');

$(document).ready(
	() => {
		$("#app").load("View/Dashboard.jsp");
		$("nav").load("View/Nav.jsp");
	}
);
	
if(new URLSearchParams(window.location.search).has("game"))
	if(!$("#game-page").length)
		window.history.pushState(null, null, "app.jsp");
		
if(new URLSearchParams(window.location.search).has("blog"))
	if(!$("#blog-page").length)
		window.history.pushState(null, null, "app.jsp");