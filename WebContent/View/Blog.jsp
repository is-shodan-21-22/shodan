<div class="content">
	<h1>
		<i class="fas fa-newspaper"></i>
		Articoli e notizie da Shodan
	</h1>
						
	<div id="all-blogs"></div>
</div>

<script>
	
	$(document).ready(
		() => {
			$.ajax(
				{
					method: "POST",
					url: "shodanBlogsServlet",
					success: (data) => {
						$("#all-blogs").html(data);
					}
				}			
			).done(
				() => {
					$(".blog-link").click(
						function() {
							window.history.pushState(null, null, "?blog=" + $(this).attr("data-blog-id"));
							$("#app").load("View/Article.jsp");
						}
					);
				}
			);
		}
	);

</script>