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
 <div class="m-5 w-50">
    <div class="d-flex justify-content-between">
        <h1><c:out value="${book.title}"/></h1>
        <a href="/books">back to shelves</a>
    </div>
    
    <c:choose>
        <c:when test="${user.id == book.user.id}">
          <h4 class="mt-5">You read  
           <span class="text-info"><c:out value="${book.title}"/></span>
            by <span class="text-success">  <c:out value="${book.author}"/></span>
          </h4>
            <div>
                <h4>Here are your thoughts:</h4>
            </div>
        </c:when>
        <c:otherwise>
         <h4 class="mt-5">
            <span class="text-danger"><c:out value="${user.name}"/></span> read 
           <span class="text-info"><c:out value="${book.title}"/></span>
            by <span class="text-success"> <c:out value="${book.author}"/></span>
         </h4>
            <div>
                <h4>Here are <c:out value="${book.user.name}"/>'s thoughts:</h4>
            </div>
        </c:otherwise>
    </c:choose>
    
    <hr />
    <p class="ml-3"><c:out value="${book.thoughts}"/></p>
    <hr />

    <div class="text-right">
        <c:if test="${user.id == book.user.id}">
            <a href="/books/${book.id}/edit" class="btn btn-light" style="box-shadow: 4px 4px black">Edit</a>
            <a href="/books/delete/${book.id}" class="btn btn-light" style="box-shadow: 4px 4px black">Delete</a>
        </c:if>
    </div>
</div>

</body>
</html>

