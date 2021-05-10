<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

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

<script src="Scripts/BlogListRoutines.js"></script>