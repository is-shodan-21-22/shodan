function openSider() {
	localStorage.setItem("sider", "open");
		
	$("#nav-logo-container").css("display", "flex");
	$("#nav-items>div>span").css("display", "inline-block");
	$("nav").css("width", "275px");
	$("main").css("margin-left", "274px");
	$("nav").addClass("open");
}
	
function closeSider() {
	localStorage.setItem("sider", "closed");
		
	$("#nav-logo-container").css("display", "none");
	$("#nav-items>div>span").css("display", "none");
	$("nav").css("width", "55px");
	$("main").css("margin-left", "55px");
	$("nav").removeClass("open");
}

if(!navigator.cookieEnabled) {
	$("#nav-close").hide();
	$(".cart-quantity").hide();
}

$(document).ready(
	() => {
		if(navigator.cookieEnabled) {
			if(localStorage.getItem("sider") == "open" || localStorage.getItem("sider") == null)
				openSider();
			else
				closeSider();
			
			if(localStorage.getItem("last-page") != null) {
				const container = "#" + localStorage.getItem("last-page").toLowerCase().split(".")[0] + "-link";
				
				$("#nav-items>div").each(
					function() {
						if($(this).hasClass("selected"))
							$(this).toggleClass("selected");
					}
				);
				
				console.log("# Shodan [Component: " + container + "]");
				
				if(container == "#game-link")				
					$("#shop-link").addClass("selected");
				else if(container == "#article-link")					
					$("#blog-link").addClass("selected");
				else			
					$(container).addClass("selected");
			}
				
			setTimeout(() => {
				refreshCart()
			}, 1000);
		} else
			$("#dashboard-link").addClass("selected");
		
		$("#nav-logo").click(
			() => {
				$("#app").load("View/Dashboard.jsp");
				if(navigator.cookieEnabled)
					localStorage.setItem("last-page", "Dashboard");
				
				$("#nav-items>div").each(
					function() {
						if($(this).hasClass("selected"))
							$(this).toggleClass("selected");
					}
				);
				
				$("#dashboard-link").addClass("selected");
			}
		);
				
		$("#nav-items>div").click(	
			function() {
				let parsed_path = window.location.href.substring(0, window.location.href.indexOf("?"));
				window.history.pushState(null, null, parsed_path);
					
				$("#nav-items>div").each(
					function() {
						if($(this).hasClass("selected"))
							$(this).toggleClass("selected");
					}
				);
					
				$(this).addClass("selected");
					
				let container = $(this).attr("id")[0].toUpperCase() + $(this).attr("id").split("-")[0].slice(1) + ".jsp";
				
				if($(this).attr("id").split("-")[0] == "admin") {
					let parsed_path;
					if(!navigator.cookieEnabled)
						parsed_path = "admin.jsp" + window.location.href.substring(window.location.href.indexOf(";"));
					else
						parsed_path = "admin.jsp";
					window.location.replace(parsed_path);
				}
				
				if(navigator.cookieEnabled && $(this) && $(this).attr("id").split("-")[0] != "admin")
					localStorage.setItem("last-page", container.split(".")[0]);
					
				$("#app").load("View/" + container).fadeIn("slow");
			}
		);
	
		$("#nav-close").click(
			() => {
				if(navigator.cookieEnabled) {
					if($("nav").hasClass("open"))
						closeSider()
					else
						openSider()
				}
			}
		);
	
		$("#logout-link").click(
			() => {
				document.cookie = "user_session=; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
				
				$.ajax(
					{
						method: "POST",
						url: "UserServlet",
						data: {
							action: "logout",
							cookie: navigator.cookieEnabled,
							jsession: window.location.href.substring(
								window.location.href.lastIndexOf("=") + 1
							)
						},
						success: () => {
							window.location.replace("index.jsp");
						}
					}
				);
			}
		);
	}
);