<div class="content">
	<div id="blog-page"></div>
</div>

<script>

	$.ajax({
	    type: "GET",
	    url: "blogServlet",
	    data: {
	    	blog: new URLSearchParams(window.location.search).get("blog")
	    },
	    success: function (data) {                        
	    	$("#blog-page").html(data);
	    },
	    error: () => location.replace("dashboard.html")
	});

</script>