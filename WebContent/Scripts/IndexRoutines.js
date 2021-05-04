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
			success: window.location.replace("app.jsp"),
			error: alert(2)
		}
	);
}

function trySignIn(){
alert(6);
}

