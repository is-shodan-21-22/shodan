$(document).ready(
	() => {
		flickering();
		
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

function flickering() {
	setTimeout(() => $(".shodan-logo").css("opacity", "0.85"), 1000);
	setTimeout(() => $(".shodan-logo").css("opacity", "0.95"), 1500);
	setTimeout(() => $(".shodan-logo").css("opacity", "0.85"), 2000);
	setTimeout(() => $(".shodan-logo").css("opacity", "0.75"), 2500);
	setTimeout(() => $(".shodan-logo").css("opacity", "0.85"), 3000);
	setTimeout(() => flickering(), 4000);
}