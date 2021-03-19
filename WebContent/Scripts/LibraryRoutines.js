$("#my-collection").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "library",
					user_id: parseInt(localStorage.getItem("uid"))
				},
				beforeSend: () => {
					$("#my-collection").html("<div class=\"loader loader-lowered\">");
				},
				success: () => {
					setTimeout(() => $("#my-collection").load("View/Library.jsp #my-collection"), 250)
				}
			}
		);
	}
);