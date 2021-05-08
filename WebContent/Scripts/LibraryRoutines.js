$("#my-collection").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				type: "GET",
				url: "GameServlet",
				data: {
					action: "library"
				},
				beforeSend: () => {
					$("#my-collection").html("<div class=\"loader loader-lowered\">");
				},
				success: () => {
					console.log("asd");
					setTimeout(() => $("#my-collection").load("View/Library.jsp #my-collection"), 250);
				},
				error: () => { 
					setTimeout(() => $("#my-collection").html(setEmptyView()), 250);
				}
			}
		);
	}
);