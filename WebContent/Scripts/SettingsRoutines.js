$("#settings-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				method: "GET",
				url: "UserServlet",
				data: {
					action: "info",
					user_id: parseInt(localStorage.getItem("uid")),
				},
				beforeSend: () => {
					$("#settings-page").html("<div class=\"loader lowered\">");
				},
				success: () => {
					setTimeout(() => {
						$("#settings-page").load("View/Settings.jsp .settings-routines");
					}, 400)
				}
			}
		);
	}
);