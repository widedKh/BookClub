<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Read Share</title>
</head>
<body>
  <div class="m-5 w-50">
   <div class="d-flex justify-content-between">
    <h1>Change your Entry</h1>
    <a href="/books">back to the shelves</a>
   </div>
    <form:form action="/books/${book.id}/edit" method="post" modelAttribute="book" class="m-5 ">
     <input type="hidden" name="_method" value="put">
        <div class="form-group row">
            <form:label path="title" class="col-sm-2 col-form-label">Title:</form:label>
            <div class="col-sm-6">
                <form:errors path="title" class="text-danger" />
                <form:input type="text" path="title" class="form-control" />
            </div>
        </div>
        <div class="form-group row">
            <form:label path="author" class="col-sm-2 col-form-label">Author: </form:label>
            <div class="col-sm-6">
                <form:errors path="author" class="text-danger" />
                <form:input type="text" path="author" class="form-control" />
            </div>
        </div>
        <div class="form-group row">
            <form:label path="thoughts" class="col-sm-2 col-form-label">My thoughts: </form:label>
            <div class="col-sm-6">
                <form:errors path="thoughts" class="text-danger" />
                <form:textarea  type="text" path="thoughts" class="form-control" rows="4"/>
            </div>
        </div>
        
         <div class="form-group text-center" >
            <input type="submit" value="Submit"class="btn btn-light" style="box-shadow: 2px 2px black" />
        </div>
    </form:form>
  </div>
</body>
</html>