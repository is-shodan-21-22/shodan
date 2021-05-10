$("#cart-container").html("");

$(function() {
	
	$.ajax(
		{
			type: "GET",
			url: "CartServlet",
			data: {
				cookie: navigator.cookieEnabled,
				endpoint: "View/Cart.jsp",
				jsession: window.location.href.substring(
					window.location.href.lastIndexOf("=") + 1
				)
			},
			beforeSend: () => {
				$("#cart-container").html("<div class=\"loader loader-lowered\">");
			},
			success: (data) => {
				setTimeout(
					() => {
						$(".content").replaceWith(data.substring(0, data.lastIndexOf("\n")))
					}, 
				350);
			},
			error: () => {
				setTimeout(
					() => {
						$("#cart-container").replaceWith(setEmptyView());
					}, 
				300);
			}
		}
	);
	
});