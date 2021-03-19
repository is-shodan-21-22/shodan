export function updateCart() {
	console.log("Updating cart...");
	if(localStorage.getItem("cart") != null) {
		$(".fa-clipboard").fadeOut("slow", () => {
			$(".cart-quantity-value").html(JSON.parse(localStorage.getItem("cart")).length);
			$(".cart-quantity-value").show(700);
		});
	}
}