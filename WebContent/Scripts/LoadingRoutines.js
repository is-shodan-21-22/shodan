$(document).ready(
	() => {
		$("body").css("background-color", "#0c0c0c");
		$("body").css("align-items", "center");
		$("body").css("justify-content", "center");
		
		console.log("# Shodan [Loading]");
					
		$("#loading-logo").fadeIn();
					
		setTimeout(
			() => {
				$("#loading-wrap").fadeIn();
			},
		500);
					
		setTimeout(
			() => {
				progress();
			},
		500);
	}
);
			
function progress() {
	if($("#loading-line").width() != $("#loading-wrap").width()) {
		$("#loading-line").width($("#loading-line").width() + 5);
		setTimeout(
			() => progress(),
		30);
	} else {
		$("#loading-logo").fadeOut();
		$("#loading-wrap").fadeOut();
		setTimeout(
			() => {
				console.log(0);
				document.head.innerHTML = '<link rel="icon" href="Static/Assets/Icon.png" type="image/x-icon">';
				document.body.setAttribute("style", "");
				$(document.body).load("app.jsp");
			},
		350);
	}
}