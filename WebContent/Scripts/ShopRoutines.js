$("#shop-games").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "shop"
				},
				beforeSend: () => {
					$("#shop-games").html("<div class=\"loader loader-lowered\">");
				},
				success: () => setTimeout(() => $("#shop-games").load("View/Shop.jsp #shop-games"), 250)
			}
		);
	}
);