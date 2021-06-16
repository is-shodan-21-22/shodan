$(document).ready(
	() => {
		console.log("# Shodan [Container: App]");
		
		if(navigator.cookieEnabled) {
			if(localStorage.getItem("last-page") != null) {
				if((localStorage.getItem("last-page") == "Game") && (new URLSearchParams(window.location.search).has("game")))
					$("#app").load("View/Game.jsp");
				else if((localStorage.getItem("last-page") == "Article") && (new URLSearchParams(window.location.search).has("blog")))
					$("#app").load("View/Article.jsp");
				else
					$("#app").load("View/" + localStorage.getItem("last-page") + ".jsp");
			} else {
				$("#app").load("View/Dashboard.jsp");
				localStorage.setItem("last-page", "Dashboard.jsp");
			}
		} else
			$("#app").load("View/Dashboard.jsp");
		$("nav").load("View/Nav.jsp");
	}
);

let parsed_path = window.location.href.substring(0, window.location.href.indexOf("?"));
		
function refreshCart() {
	if(navigator.cookieEnabled) {
		$(".cart-quantity-value").text(localStorage.getItem("cart"));
		if(localStorage.getItem("cart") != null) {
			$(".fa-clipboard").fadeOut("slow", 
				() => {
					$(".cart-quantity-value").show(700);
				}
			);
		}
	}
}

function deleteCart() {
	$(".cart-quantity-value").fadeOut("slow", 
		() => {
			$(".fa-clipboard").fadeIn("slow");
		}
	);
	localStorage.removeItem("cart");
}

function updateCart() {
	if(localStorage.getItem("cart") != null)				
		localStorage.setItem("cart", parseInt(localStorage.getItem("cart")) + 1);
	else
		localStorage.setItem("cart", 1);
	refreshCart();
}

function setEmptyView(error = "Non c'è ancora nulla qui...") {
	let view = "<div class=\"empty-view\"><i class=\"far fa-folder-open\"></i>";
	view += "<div>" + error + "</div></div>";
	
	return view;
}