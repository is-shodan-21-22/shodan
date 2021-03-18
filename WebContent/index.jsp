<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1" 
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Shodan</title>
		
		<!-- Index Stylesheet -->
		<link rel="stylesheet" href="Style/Index.css">
		
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<!-- Login Status -->
		<script>
			if(localStorage.hasOwnProperty("uid"))
				window.location.replace('dashboard.jsp');
		</script>
	</head>
	
	<body>
		<form onsubmit="tryLogin(); return false">
			<h1>Accesso</h1>
 			<input id="login-username" required placeholder="Username" type="text" name="login_username"><br />
 			<input id="login-password" required placeholder="Password" type="password" name="login_password"><br />
 			<input id="login-submit" type="submit" name="login_submit">
		</form>
	</body>
		
	<script>
		
		function tryLogin() {
			$.ajax(
				{	
					method: "POST",
					data: {
						username: $("#login-username").val(), 
						password: $("#login-password").val()
					},
					url: "loginServlet"
				}
			).done(
				(data) => {
					console.log(data);
					if(!data.includes("unknown")){
						localStorage.setItem("uid", data);
						location.reload();
					} else
						alert("L'utente non esiste");
				}
			);
		}
		
	</script>
</html>