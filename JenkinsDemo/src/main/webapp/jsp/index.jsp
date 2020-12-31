<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<title>Hola Mundo</title>
</head>
<body>
	<div class="container mt-1 mb-5">
		
		<div class="jumbotron shadow text-center">
			<h1 class="card-title">Hola Mundo</h1>
			<a href="/JenkinsDemo">
				<img class="rounded-circle shadow mb-3" src="img/camaleon.jpg" height="150" width="150" alt="camaleon.jpg"/>
			</a>
			<p>Aplicación web dinámica de prueba</p>
		</div>
		
		<div class="card shadow px-3">
			<div class="card-body">
				<p>Buscar usuario:</p>
				<form action="Resultados" method="post">
					<div class="row flex-nowrap">
						<input type="search" name="nombre" class="form-control"/>
						<input type="submit" value="Buscar" class="btn btn-sm btn-primary ml-2"/>
					</div>
				</form>
				<c:if test="${not empty empleados}">
					<table class="table mt-3">
						<thead>
							<tr>
								<td>Id</td>
								<td>Nombre</td>
								<td>Salario</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="empleado" items="${empleados}">
								<tr>
									<td>${empleado.id}</td>
									<td>${empleado.nombre} ${empleado.apellido}</td>
									<td>$${empleado.salario}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
			
				
					
			
		
	</div>
</body>
</html>