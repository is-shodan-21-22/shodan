<div class="content">
	<h1>
		<i class="fas fa-gamepad"></i>
		La mia collezione
	</h1>
	
	<div id="my-collection" class="games"></div>
</div>

<script>
	
	$(document).ready(
		() => {
			$.ajax(
				{
					method: "POST",
					url: "myCollectionServlet",
					data: {
						user_id: localStorage.getItem("uid")
					},
					success: (data) => {
						$("#my-collection").html(data);
					},
					error: (data, status) => {
						console.log(status);
					}
				}
			).done(
				() => {
					console.log("done");
					$(".game-container").click(
						function() {
							window.history.pushState(null, null, "?game=" + $(this).attr("data-game-id"));
							$("#app").load("containers/game-view.html");
						}
					);
				}
			);
		}
	);
	
</script>