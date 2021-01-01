<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<title>Hola Mundo</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center mt-5">
			<div class="card shadow rounded text-center py-4 col-6">
				<h1 class="card-title">Hola Mundo</h1>
				<a href="<c:url value="/"/>">
					<img class="rounded-circle shadow mb-3" src="<c:url value="/img/camaleon.jpg"/>" height="150" width="150" alt="face.jpg"/>
				</a>
				<p>Aplicación web dinámica de prueba</p>
				<p>${date}</p>
			</div>
		</div>
	</div>
</body>
</html>