$(document).ready(
	() => {
		if(getCartLength() == 0) {
			console.log("Carrello vuoto...");
		
			$("#cart-delete").hide();
			$("#cart-pay").hide();
		} else {
			console.log("Carrello non vuoto...");
		
			const cart = populateCart();
			populateTable(cart);
		
			$("#cart-delete").click(
				() => {
					console.log("Svuoto il carrello...");
					
					deleteCart();
					
					$(".cart-game-row").remove();
					$(".cart-total").text("0€");
					$("#cart-delete").hide();
					$("#cart-pay").hide();
				}
			);
		
			$("#cart-pay").click(
				() => {
					const price = parseInt($(".cart-total").text().split("€")[0]);
					console.log("Effettuo il pagamento di " + price + "€...");
				
					$.ajax(
						{
							method: "GET",
							url: "UserServlet",
							data: {
								action: "purchase",
								user_id: parseInt(localStorage.getItem("uid")),
								price: price,
								games: getCartIds().join("-")
							},
							success: (data) => {
								console.log(data);
								
								if(data.includes("successo")) {
									deleteCart();
									refreshCart();
								}
												
								$(".cart-status>span").html(data);
								$(".cart-status").show(600);
								
								if(data.includes("successo")) {
									$(".cart-game-row").remove();
									$(".cart-total").text("0€");
									$("#cart-delete").hide();
									$("#cart-pay").hide();
								}
								
								setTimeout(() => $(".cart-status").hide("slow"), 4000);
							}
						},
						
					);
				}
			);
		}
		
	}
);

function getCartIds() {
	const games = JSON.parse(localStorage.getItem("cart"));
	return [...new Set(games.map(item => item.game_id))];
}

function getCartLength() {
	const games = JSON.parse(localStorage.getItem("cart"));
	
	return (games == null) ? 0 : getCartIds();
}

function populateCart() {
	const games = JSON.parse(localStorage.getItem("cart"));
	const uniqueIds = [...new Set(games.map(item => item.game_id))];

	let items = [];

	uniqueIds.forEach((id) => {
		let name = null;
		let price = 0;
		let quantity = 0;
		let total = 0;
		
		games.forEach((game) => {
			console.log("Nuovo gioco: " + game.game_id);
			
			if(game.game_id == id) {
				name = game.game_name;
				price = parseInt(game.game_price);
				total += parseInt(game.game_price);
				quantity += 1;
			}
		});
		
		items.push(
			{
				id: id,
				name: name,
				price: price,
				quantity: quantity,
				total: total
			}
		);
	});
	
	return items;
}

function parseGameRow(game) {
	let result = "";
	
	result += "<td><em>" + game.name + "</em></td>";
	result += "<td>" + game.price + "€</td>";
	result += "<td>" + game.quantity + "</td>";
	result += "<td><strong>" + game.total + "€</strong></td>";
	
	return result;
}

function populateTable(games) {
	let total = 0;
	
	games.forEach((game) => {
		total += game.total;
		$("#cart").append("<tr class='cart-game-row'>" + parseGameRow(game) + "</tr>");
	});
	
	$("#cart").append("<tr class='last-row'><td /><td /><td /><td class='cart-total'>" + total + "€</td></tr>");
}