function updateCart() {
	console.log("Updating cart...");
	if(localStorage.getItem("cart") != null) {
		$(".fa-clipboard").fadeOut("slow", () => {
			$(".cart-quantity-value").html(JSON.parse(localStorage.getItem("cart")).length);
			$(".cart-quantity-value").show(700);
		});
	}
}

function addToCart(trigger) {
	const cart = JSON.parse(localStorage.getItem("cart"));
	let newCart = cart == null ? [] : cart;
						
	newCart[cart == null ? 0 : cart.length] = {
			game_id: trigger.parent().attr("data-game-id"),
			game_name: trigger.parent().attr("data-game-name"),
			game_price: trigger.parent().attr("data-game-price")
	};

	console.log(newCart);
						
	$(".game-confirm>span").html("Aggiunto <strong>" + trigger.parent().attr("data-game-name") +"</strong> al carrello!");
	$(".game-confirm").show(225);
						
	setTimeout(() => $(".game-confirm").hide("slow"), 4000);
						
	localStorage.setItem("cart", JSON.stringify(newCart));
						
	updateCart();
						
	return false;
}

function addToCartWithoutParsing(id, name, price) {
	const cart = JSON.parse(localStorage.getItem("cart"));
	let newCart = cart == null ? [] : cart;
						
	newCart[cart == null ? 0 : cart.length] = {
			game_id: id,
			game_name: name,
			game_price: price
	};

	console.log(newCart);
				
	$("#add-to-cart").html("Aggiunto <strong>" + name +"</strong> al carrello!");
						
	setTimeout(
		() => {
			$("#add-to-cart").html("Aggiungi un'altra copia al carrello");	
		},
	4000);
				
	localStorage.setItem("cart", JSON.stringify(newCart));
						
	updateCart();
						
	return false;
}