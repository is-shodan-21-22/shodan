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
		
		$(document).ajaxComplete(
			() => {
				const user_id = parseInt(localStorage.getItem("uid"));
			
				$("#settings-submit-email").off().click(
					() => {
						$.ajax(
							{
								method: "POST",
								url: "SettingsServlet",
								data: {
									action: "updateEmail",
									user_id: user_id,
									email: $("#settings-input-email").val()
								},
								success: (data) => {
									$(".settings-status").html(data);
									$(".settings-status").show();
									setTimeout(() => $(".settings-status").hide(), 2500);
								}
							}
						);
					}
				);
				
				$("#settings-submit-password").off().click(
					() => {
						$.ajax(
							{
								method: "POST",
								url: "SettingsServlet",
								data: {
									action: "updatePassword",
									user_id: user_id,
									old_password: $("#settings-input-old-password").val(),
									new_password: $("#settings-input-new-password").val(),
									new_password_again: $("#settings-input-new-password-again").val(),
								},
								success: (data) => {
									$(".settings-status").html(data);
									$(".settings-status").show();
									setTimeout(() => $(".settings-status").hide(), 2500);
								}
							}
						);
					}
				);
			}
		);
	}
);