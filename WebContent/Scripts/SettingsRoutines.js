$("#settings-page").html("");

$(document).ready(
	() => {
		$.ajax(
			{
				method: "GET",
				url: "UserServlet",
				data: {
					action: "info",
					cookie: navigator.cookieEnabled,
					jsession: window.location.href.substring(
						window.location.href.lastIndexOf("=") + 1
					),
					endpoint: "View/Settings.jsp"
				},
				beforeSend: () => {
					$("#settings-page").html("<div class=\"loader loader-lowered\">");
				},
				success: (data) => {
					setTimeout(() => {
						$(".content").replaceWith(data.substring(0, data.lastIndexOf("\n")))
					}, 400)
				}
			}
		);
	}
);

function tryEmailChange() {
	$.ajax(
		{
			method: "POST",
			url: "SettingsServlet",
			data: {
				action: "updateEmail",
				cookie: navigator.cookieEnabled,
				jsession: window.location.href.substring(
					window.location.href.lastIndexOf("=") + 1
				),
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

function tryPasswordChange() {
	const password_regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}$/;
	
	if(
		$("#settings-input-new-password").val().match(password_regex) &&
		$("#settings-input-new-password-again").val().match(password_regex)
	) {
		$.ajax(
			{
				method: "POST",
				url: "SettingsServlet",
				data: {
					action: "updatePassword",
					cookie: navigator.cookieEnabled,
					jsession: window.location.href.substring(
						window.location.href.lastIndexOf("=") + 1
					),
					old_password: $("#settings-input-old-password").val(),
					new_password: $("#settings-input-new-password").val(),
					new_password_again: $("#settings-input-new-password-again").val()
				},
				success: (data) => {
					$(".settings-status").html(data);
					$(".settings-status").show();
					setTimeout(() => $(".settings-status").hide(), 2500);
				}
			}
		);
	} else {
		$(".settings-status").html("La password deve avere almeno cinque caratteri, di cui almeno una lettera e un numero.");
		$(".settings-status").show();
		setTimeout(() => $(".settings-status").hide(), 2500);
	}
}