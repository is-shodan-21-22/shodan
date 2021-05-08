
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
		
function refreshCart() {
	if(localStorage.getItem("cart") != null) {
		$(".fa-clipboard").fadeOut("slow", () => {
			$(".cart-quantity-value").html(JSON.parse(localStorage.getItem("cart")).length);
			$(".cart-quantity-value").show(700);
		});
	}
}

function deleteCart() {
	$(".cart-quantity-value").fadeOut("slow", () => {
		$(".fa-clipboard").fadeIn("slow");
	});
	
	localStorage.removeItem("cart");
}

function updateCart(id, name, price) {
	const cart = JSON.parse(localStorage.getItem("cart"));
	let newCart = cart == null ? [] : cart;
						
	newCart.push(
		{
			game_id: id,
			game_name: name,
			game_price: price
		}
	);

	console.log(newCart);

	localStorage.setItem("cart", JSON.stringify(newCart));
						
	refreshCart();
						
	return false;
}

function setEmptyView() {
	let view = "<div class=\"empty-view\"><i class=\"far fa-folder-open\"></i>"
	view += "<div>Non c'è ancora nulla qui...</div></div>";
	
	return view;
}