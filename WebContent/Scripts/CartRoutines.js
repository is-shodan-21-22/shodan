$(document).ready(
	() => {
		const cart = populateCart();
		populateTable(cart);
		
		$("#cart-delete").click(
			() => {
				deleteCart();
				$(".cart-game-row").remove();
				$(".cart-total").text("0€");
			}
		);
	}
);

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