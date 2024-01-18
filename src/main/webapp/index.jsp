<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test	</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
<form novalidate method="POST">
<input type="text" name="major" placeholder="Search by major" value="" style="width:300px"> <button formaction="search"><i class="fa-solid fa-magnifying-glass"></i> Search</button>
<button formaction="new"><i class="fa-solid fa-plus"></i> New contact</button>
<table id="tableStudent" class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>STT</th>
			<th>MSSV</th>
			<th>Họ tên</th>
			<th>Điểm</th>
			<th>Chuyên ngành</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
	<c:set var="count" value="${0}" scope="request"/>
		<c:forEach items="${students}" var="item">
		<c:set var="count" value="${count+1}" scope="request"/>
			<tr>
				<th scope="row"><c:out value="${requestScope.count}"/></th>
				<td>SV${item.id}</td>
				<td>${item.name}</td>
				<td>${item.mark}</td>
				<td>${item.major}</td>
				<td><a href="edit?id=${item.id}"><i class="fa-solid fa-marker"></i></a> - <a href="delete?id=${item.id}"><i class="fa-solid fa-trash"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
${messages}
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/9fe08cb9b1.js" crossorigin="anonymous"></script>
</body>
</html>