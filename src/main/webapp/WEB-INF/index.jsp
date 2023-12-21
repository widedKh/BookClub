<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Read share</title>
</head>
<body>
 <div class="m-5">
   <h1>Book Club</h1>
   <p>A place for friends to share thoughts on books</p>
   <div class="d-flex">
   <form:form action="/register" method="post" modelAttribute="newUser" >

	<table class="table table-bordered ">
    <thead>
        <tr>
            <th colspan="2" class="text-center">Register</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td scope="col">User name:</td>
            <td>
                <form:errors path="name" class="text-danger" />
                <form:input class="form-control" path="name" />
            </td>
        </tr>
        <tr>
            <td  scope="col">Email:</td>
            <td>
                <form:errors path="email" class="text-danger" />
                <form:input class="form-control" path="email" />
            </td>
        </tr>
        <tr>
            <td scope="col">Password:</td>
            <td >
                <form:errors path="password" class="text-danger" />
                <form:input type="password" class="form-control" path="password" />
            </td>
        </tr>
        <tr>
            <td scope="col">Confirm password:</td>
            <td >
                <form:errors path="confirm" class="text-danger" />
                <form:input type="password" class="form-control" path="confirm" />
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-center">
                <input class="col-sm-10 btn btn-light" type="submit" value="Submit" />
            </td>
        </tr>
    </tbody>
</table>

</form:form>

<form:form action="/login" method="post" modelAttribute="newLogin" class="mt-5">

	<table class="table table-bordered ml-5">
		<thead>
	    	<tr>
	            <th colspan="2" class="text-center">Log In</th>
	        </tr>
	    </thead>
	    <thead>
	        <tr>
	            <td scope="col">Email:</td>
	            <td >
	            	<form:errors path="email" class="text-danger"/>
					<form:input class="form-control" path="email"/>
	            </td>
	        </tr>
	        <tr>
	            <td scope="col">Password:</td>
	            <td >
	            	<form:errors path="password" class="text-danger"/>
					<form:input type="password" class="form-control" path="password"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2><input  type="submit" value="Submit" class="col-sm-10 btn btn-light" /></td>
	        </tr>
	    </thead>
	</table>
</form:form>
</div>
</div>
</body>
</html>