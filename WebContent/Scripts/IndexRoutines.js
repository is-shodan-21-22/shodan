
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
				window.history.pushState(null, null, data);
				
				if(data == null)
					window.location.replace("app.jsp");
				else
					window.location.replace(data);
			},
			error: (data) => {
				console.log("Errore nel login");
				$("#login-message").html(data.responseText);
				$("#login-message").css("color", "red");
				$("#login-fail").show();
			}	
		}
	);
}

function trySignIn(){
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
				console.log("Registrazione avvenuta con successo: " + data);
				$("#signin-message").html(data);
				$("#signin-message").css("color", "green");
				$("#signin-message").show();
			},
			error: (data) => {
				console.log("Fallimento nella registrazione: " + data.responseText);
				$("#signin-message").html(data.responseText);
				$("#signin-message").css("color", "red");
				$("#signin-message").show();
			}
		}
	);
}

