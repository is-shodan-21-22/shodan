$(document).ready(
	() => {
		$("#app").load("View/Dashboard.jsp");
		$("nav").load("View/Nav.jsp");
	}
);

let parsed_path = window.location.href.substring(0, window.location.href.indexOf("?"));
	
if(new URLSearchParams(window.location.search).has("game"))
	if(!$("#game-page").length)
		window.history.pushState(null, null, parsed_path);
		
if(new URLSearchParams(window.location.search).has("blog"))
	if(!$("#blog-page").length)
		window.history.pushState(null, null, parsed_path);
		
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

function setEmptyView() {
	let view = "<div class=\"empty-view\"><i class=\"far fa-folder-open\"></i>";
	view += "<div>Non c'è ancora nulla qui...</div></div>";
	
	return view;
}