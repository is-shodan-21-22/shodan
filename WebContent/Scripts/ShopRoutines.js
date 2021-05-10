$("#shop-games").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					endpoint: "View/AJAX_Components/GameList.jsp"
				},
				beforeSend: () => {
					$("#shop-games").html("<div class=\"loader loader-lowered\">");
				},
				success: (data) => {
					setTimeout(() => {
						$("#shop-games").html(data)
					}, 250);
				},
				error: () => {
					setTimeout(() => $("#shop-games").html(setEmptyView()), 250);
				}
			}
		);
	}
);