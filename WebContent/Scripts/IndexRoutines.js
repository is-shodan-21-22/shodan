
$(document).ready(
	() => {

		$.ajax(
			{
				method: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					limit: 3,
					endpoint: "View/AJAX_Components/GameSlideshow.jsp"
				},
				beforeSend: () => {
					$(".left").html("<div class='loader-container'><div class=\"loader\"></div>");
				},
				success: (data) => {
					setTimeout(() => {
						$(".left").html(data);
					}, 400);
				}
			}
		);
		
		$.ajax(
			{
				method: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					limit: 3,
					order: "DESC",
					endpoint: "View/AJAX_Components/GameSlideshow.jsp"
				},
				beforeSend: () => {
					$(".right").html("<div class='loader-container'><div class=\"loader\"></div>");
				},
				success: (data) => {
					setTimeout(() => {
						$(".right").html(data);
					}, 400)
				}
			}
		);
						
	}
);

function tryLogin(){
	$.ajax(
		{
			method: "POST",
			url: "LoginServlet",
			data: {
				username: $("#login-username").val(),
				password: $("#login-password").val(),
				cookie: navigator.cookieEnabled
			},
			success: (data) => {
				if(data.length == 0)
					window.history.pushState(null, null, "app.jsp");
				else
					window.history.pushState(null, null, data);
				
				if(navigator.cookieEnabled)
					localStorage.setItem("last-page", "Dashboard");
				
				$(document.body).fadeOut(400, 
					() => {
						$(document.body).load("View/AJAX_Components/Loading.jsp");
						$(document.body).fadeIn();
					}
				);
			},
			error: (data) => {
				$("#login-message").html(data.responseText);
				$("#login-message").css("color", "red");
				$("#login-fail").show();
			}	
		}
	);
}

function trySignIn(){
	const password_regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}$/;
	
	if($("#signin-password").val().match(password_regex)) {
		$.ajax(
			{
				method: "POST",
				url: "SignInServlet",
				data: {
					username: $("#signin-username").val(),
					password: $("#signin-password").val(),
					password2: $("#signin-password-again").val(),
					email: $("#signin-email").val()
				},
				success: (data) => {
					$("#signin-message").html(data);
					$("#signin-message").css("color", "green");
					$("#signin-message").show();
				},
				error: (data) => {
					$("#signin-message").html(data.responseText);
					$("#signin-message").css("color", "red");
					$("#signin-message").show();
				}
			}
		);
	} else {
		$("#signin-message").css("color", "red");
		$("#signin-message").html(
			"La password non rispetta i criteri.<br/>" +
			"Sono necessari almeno cinque caratteri,<br/>" +
			"di cui per lo meno un numero e una lettera."
		);
	}
}

