function openSider() {
	localStorage.setItem("sider", "open");
		
	$("#nav-items>div>span").css("display", "inline-block");
	$("nav").css("width", "275px");
	$("main").css("margin-left", "274px");
	$("nav").addClass("open");
}
	
function closeSider() {
	localStorage.setItem("sider", "closed");
		
	$("#nav-items>div>span").css("display", "none");
	$("nav").css("width", "55px");
	$("main").css("margin-left", "55px");
	$("nav").removeClass("open");
}

$(document).ready(
	() => {
		if(localStorage.getItem("sider") == "open" || localStorage.getItem("sider") == null)
			openSider();
		else
			closeSider();
				
		$("#nav-items>div").click(	
			function() {
				window.history.pushState(null, null, "app.jsp");
					
				$("#nav-items>div").each(
					function() {
						if($(this).hasClass("selected"))
							$(this).toggleClass("selected");
					}
				);
					
				$(this).addClass("selected");
					
				let container = $(this).attr("id")[0].toUpperCase() + $(this).attr("id").split("-")[0].slice(1) + ".jsp";
				
				if($(this).attr("id").split("-")[0] == "admin")
					window.location.replace("admin.jsp");
					
				$("#app").load("View/" + container);
			}
		);
	
		$("#nav-close").click(
			() => {
				if($("nav").hasClass("open"))
					closeSider()
				else
					openSider()
			}
		);
	
		$("#logout-link").click(
			() => {
				localStorage.removeItem("uid");
				window.location.replace('index.jsp');
			}
		);
	}
);