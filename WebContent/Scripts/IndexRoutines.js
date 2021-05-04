if(document.cookie){		
	window.location.replace("app.jsp");
}
		
$(document).ready(
	() => {

		$.ajax(
			{
				method: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					limit: 5
				},
				beforeSend: () => {
					$(".left").html("<div class='loader-container'><div class=\"loader\"></div>");
				},
				success: () => {
					setTimeout(() => {
						$(".left").load("index.jsp .left");
					}, 400)
				}
			}
		);
		
		$.ajax(
			{
				method: "GET",
				url: "GameServlet",
				data: {
					action: "shop",
					limit: 5,
					order: "DESC"
				},
				beforeSend: () => {
					$(".right").html("<div class='loader-container'><div class=\"loader\"></div>");
				},
				success: () => {
					setTimeout(() => {
						$(".right").load("index.jsp .right");
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
				password: $("#login-password").val()
			},
			success: () => {
				console.log("Login effettuato con successo");
				window.location.replace("app.jsp");
			},
			error: () => {
				console.log("Errore nel login");
				$("#login-fail").fadeIn("fast");
			}	
		}
	);
}

function trySignIn(){
alert(6);
}

