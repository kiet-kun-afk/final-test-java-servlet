<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
<h1>Contact form</h1> <br>
<form novalidate method="POST">
<input type="text" name="id" value="${student.id}" placeholder="Id?" style="width:300px" readonly> <br>
<input type="text" name="name" value="${student.name}" placeholder="Name?" style="width:300px"> <br>
<input type="text" name="mark" value="${student.mark}" placeholder="Mark?" style="width:300px"> <br>
<input type="text" name="major" value="${student.major}" placeholder="Major?" style="width:300px"> <br>
<button formaction="edit">Submit</button>
</form>
${messages}
<a type="button" href="index">Back home</a>
</body>
</html>