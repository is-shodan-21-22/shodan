<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">

	<h1>
		<i class="fas fa-newspaper"></i>
		Articoli e notizie da Shodan
	</h1>
						
	<div class="blog">
		<c:forEach items="${articles}" var="article">
			<div class="article-container">
				<div class="article">
					<h1> ${article.title} </h1>
					<div> ${article.shortTitle} </div>
					<span class='blog-link' data-blog-id="${article.id}">
						<i class="fas fa-caret-square-right"></i> Leggi la notizia
					</span>
				</div>
			 </div>
		</c:forEach>
	</div>
	
</div>

<script src="Scripts/BlogRoutines.js"></script>