$("#cart-container").html("");

$(document).ready(
	() => {
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
		).done(
			() => {
				$(document).off().on("click", "#cart-pay",
					() => {
						$.ajax(
							{
								type: "POST",
								url: "CartServlet",
								data: {
									action: "pay",
									cookie: navigator.cookieEnabled,
									jsession: window.location.href.substring(
										window.location.href.lastIndexOf("=") + 1
									),
									total: $(".last-row-total").text().split(" ")[1]
								},
								success: () => {
									$(".cart-item").hide();
									$("#cart-pay").hide();
									$("#cart-delete").hide();
									
									$(".last-row-tag").text("L'acquisto è stato effettuato con successo!");
									$(".last-row-total").text("");
									deleteCart();
								},
								error: () => {
									$(".last-row-info").text("Non hai abbastanza soldi.");
								}
							}
						);
					}
				);
				
				$(document).on("click", "#cart-delete",
					() => {
						$(".cart-item").hide();
						$("#cart-pay").hide();
						$("#cart-delete").hide();
						
						$.ajax(
							{
								type: "POST",
								url: "CartServlet",
								data: {
									action: "delete",
									cookie: navigator.cookieEnabled,
									jsession: window.location.href.substring(
										window.location.href.lastIndexOf("=") + 1
									)
								},
								success: () => {
									$(".last-row-tag").text("Il carrello è stato svuotato!");
									$(".last-row-total").text("");
									deleteCart();
								}
							}
						);
					}
				);
			}
		);
	}
);