<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title>Web Demo</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center py-5">
			<div class="card rounded shadow col-8 py-4 text-center">
				<h2>Web Demo</h2>
				<c:url value="/image" var="img">
						<c:param name="id">${avatarIndex}</c:param>
					</c:url>
				<a href="${img}">
					<img class="rounded-circle mx-auto my-3 img-fluid" src="${img}" height="150" width="150" alt="${img}"/>
				</a>
				<p>Aplicación web de muestra</p>
				<div class="container">
					<form id="avatarForm" action="change" method="post" class="mb-3">
						<div class="row flex-nowrap justify-content-end">
							<label class="col-4 mr-2">Seleccionar avatar</label>
							<select class="custom-select" name="index" onchange="cambiarImagen();">
								<c:forEach var="fileName" items="${fileNames}" varStatus="loop">
									<c:choose>
										<c:when test="${loop.index eq avatarIndex}">
											<option value="${loop.index}" selected>${fileName}</option>
										</c:when>
										<c:otherwise>
											<option value="${loop.index}">${fileName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</form>
					<form action="upload" method="post" enctype="multipart/form-data">
						<div class="row flex-nowrap justify-content-end">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="customFile" name="image" size="50" accept="image/png, image/jpeg" multiple="multiple" required>
							  	<label class="custom-file-label" for="customFile" data-browse="Explorar">Seleccionar imagen</label>
							</div>
							<button type="submit" class="btn btn-small btn-info ml-2"><i class="fas fa-upload"></i></button>
						</div>
						<p class="${bsClass} mt-3">${mensaje}</p>
					</form>
				</div>
			</div>
			<div class="d-none text-center text-muted mt-5">
					<p>${paths}</p>
				</div>
		 </div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.min.js"></script>
    <script src="https://kit.fontawesome.com/aaf6652f81.js"></script>
    <script>
    	$(document).ready(function () {
    	  bsCustomFileInput.init();
    	})
    	function cambiarImagen() {
    		  document.getElementById("avatarForm").submit();
    	  }
    </script>
</body>
</html>