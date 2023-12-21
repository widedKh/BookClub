<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Read share</title>
</head>
<body>

	<div class="m-5  w-75">
	 <div class="d-flex justify-content-between mb-3">
      <h4>Hello, <c:out value="${user.name}"/>. Welcome to</h4>
     
      <a href="/logout">logout</a>
     </div>
      <h1>The Book Broker</h1>
     <div class="d-flex justify-content-between mb-3">
       <h3>Books from everyone's shelves:</h3>
       <a href="/books/new">+ Add to my shelf</a>
     </div>
   <table class="table table-striped table-bordered">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author Name</th>
        <th>Posted By</th>
        <th>Action</th>
    </tr>
    <c:forEach var="book" items="${books}">
				
				<c:if test="${user.id!=book.user.id}">
					<tr>
						<td><c:out value="${book.id}"/></td>
						<td><a href="books/${book.id}"><c:out value="${book.title}"/></a></td>
						<td><c:out value="${book.author}"/></td>
						<td><c:out value="${book.user.name}"/></td>
						<c:if test="${user==book.user}">
							<td><a href="books/${book.id}/edit">edit</a> <a href="books/${book.id}/delete">delete</a></td>
						</c:if>
						<c:if test="${user!=book.user}">
							<td><a href="books/${book.id}/borrow">borrow</a></td>
						</c:if>
					</tr>
				</c:if>
				
			</c:forEach>
		
		</table>
		
		<h3>Books I'm Borrowing</h3>
		
		<table class="table">
		
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author Name</th>
				<th>Owner</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${books}">
			
				<c:if test="${user.id==book.user.id}">
					<tr>
						<td><c:out value="${book.id}"/></td>
						<td><a href="books/${book.id}"><c:out value="${book.title}"/></a></td>
						<td><c:out value="${book.author}"/></td>
						<td><c:out value="${book.user.name}"/></td>
						<c:if test="${user!=book.user}">
							<td><a href="books/${book.id}/return">return</a></td>
						</c:if>
					</tr>
				</c:if>
				
			</c:forEach>
		
		</table>



</div>


</body>
</html>