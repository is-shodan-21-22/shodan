<!-- 

	Siderbar
	
-->

<div id="close-sider">
	<i class="fas fa-arrows-alt-h"></i>
</div>
			
<div class="links-container">
	<div id="Dashboard-link" class="sider-item selected">
		<i class="fas fa-store-alt"></i>
		<span>Dashboard</span>
	</div>
			
	<div id="Shop-link" class="sider-item">
		<i class="fas fa-comments-dollar"></i>
		<span>Negozio</span>
	</div>
					
	<div id="Library-link" class="sider-item">
		<i class="fas fa-gamepad"></i>
		<span>Libreria</span>
	</div>
	
	<div id="Blog-link" class="sider-item">
		<i class="fas fa-newspaper"></i>
		<span>Notizie</span>
	</div>
					
	<div id="Settings-link" class="sider-item">
		<i class="far fa-address-book"></i>
		<span>Impostazioni</span>
	</div>
	
	<div id="Cart-link" class="sider-item">
		<i class="fas fa-shopping-cart"></i>
		<span>Carrello</span>
	</div>
	
	<div id="Admin-link" class="sider-item">
		<i class="fas fa-users-cog"></i>
		<span>Admin</span>
	</div>
					
	<div id="logout-link">
		<i class="far fa-dizzy"></i>
		<span>Esci</span>
	</div>
</div>

<script>
	/*
	
		One-Page Routine
		Load the specified component when clicked on the sidebar
	
	*/
	
	$(document).ready(
		() => {
			if(localStorage.getItem("sider") == "open" || localStorage.getItem("sider") == null)
				$(".sider").addClass("open");
			else
				closeSider();
		}
	);
	
	$(".links-container>div").click(	
		function() {
			window.history.pushState(null, null, "dashboard.jsp");
			
			$(".links-container>div").each(
				function() {
					if($(this).hasClass("selected"))
						$(this).toggleClass("selected");
				}
			);
			
			$(this).addClass("selected");
			
			let container = $(this).attr("id").split("-")[0] + ".jsp";
		
			if($(this).attr("id").split("-")[0] == "admin")
				window.location.replace("admin.jsp");
			
			$("#app").load("View/" + container);
		}
	);
	
	/*
	
		Siderbar Routine
		When $(#close-sider) is clicked, either open or close the sidebar
		.. then save the choice in the local storage
	
	*/

	$("#close-sider").click(
		() => {
			if($(".sider").hasClass("open"))
				closeSider()
			else
				openSider()
		}
	);
	
	function openSider() {
		console.log("Opening sider...");
		
		localStorage.setItem("sider", "open");
		
		$(".links-container>div>span").css("display", "inline-block");
		$(".sider").css("width", "275px");
		$(".main").css("margin-left", "274px");
		$(".sider").addClass("open");
	}
	
	function closeSider() {
		console.log("Closing sider...");
		
		localStorage.setItem("sider", "closed");
		
		$(".links-container>div>span").css("display", "none");
		$(".sider").css("width", "55px");
		$(".main").css("margin-left", "55px");
		$(".sider").removeClass("open");
	}
	
	/*
	
		Logout Routine
		Checks if the user has the logged_in boolean set to true
	
	*/
	
	$("#logout-link").click(
		() => {
			localStorage.removeItem("uid");
			window.location.replace('index.jsp');
		}
	);

</script>