<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
		
			<div class="card rounded shadow col-md-8 py-4 text-center">
			
				<a href="<c:url value="/"/>" class="text-decoration-none"><h2>Web Demo</h2></a>
				
				<c:url value="/image" var="img">
					<c:param name="file">${avatar}</c:param>
				</c:url>
				<a href="${img}">
					<img class="rounded-circle mx-auto my-3" src="${img}" height="150" width="150" alt="${img}"/>
				</a>
				
				<p>Selecciona tu avatar</p>
				
				<div class="container">
				
					<!-- AVATAR FORM -->
					<form id="avatarForm" action="change" method="post" class="mb-3 mx-auto w-75">
						<div class="row flex-nowrap justify-content-center">
							<label class="col-4 mr-2 text-right col-form-label">Avatar</label>
							<select class="custom-select" name="avatar" onchange="cambiarImagen();">
								<c:forEach var="imagen" items="${imagenes}" varStatus="loop">
									<c:set var="nombre" value="${fn:substringBefore(imagen, '.')}"/>
									<c:choose>
										<c:when test="${imagen eq avatar}">
											<option value="${imagen}" selected>${nombre}</option>
										</c:when>
										<c:otherwise>
											<option value="${imagen}">${nombre}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</form>
					<!-- /AVATAR FORM -->
					
					<!-- UPLOAD FORM -->
					<form id="uploadForm" action="upload" method="post" enctype="multipart/form-data">
						<div class="row flex-nowrap justify-content-center">
							<div class="custom-file w-75">
								<input type="file" class="custom-file-input" id="customFile" name="image" size="50" accept="image/*" required>
							  	<label class="custom-file-label pr-5" for="customFile" data-browse="Explorar">Subir imágen</label>
							</div>
							<label id="preview" class="col-form-label mx-3"></label>
							<button type="submit" class="btn btn-small btn-info"><i class="fas fa-upload"></i></button>
						</div>
					</form>
					<!-- /UPLOAD FORM -->
					
					<!-- FEEDBACK MESSAGE -->
					<c:if test="${not empty mensaje}">
						<div class="alert ${bsClass} alert-dismissible fade show w-75 mx-auto mt-3" role="alert">
							${mensaje}
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							 </button>
						</div>
					</c:if>
					<!-- /FEEDBACK MESSAGE -->
				</div>
				
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
    	
    	const input = document.querySelector('input[type=file]');
    	const preview = document.querySelector('#preview');
    	
    	input.addEventListener('change', updateImageDisplay);
    	
    	function updateImageDisplay() {
    		while(preview.firstChild) {
    		    preview.removeChild(preview.firstChild);
    		  }
    		const file = input.files[0];
    		console.log(file);
    		const img = document.createElement('img');
    		img.src=URL.createObjectURL(file);
    		img.width="35";
    		img.height="35";
    		preview.appendChild(img);
    		
    	}
    	
    	
    	function returnFileSize(number) {
    		  if(number < 1024) {
    		    return number + 'bytes';
    		  } else if(number >= 1024 && number < 1048576) {
    		    return (number/1024).toFixed(1) + 'KB';
    		  } else if(number >= 1048576) {
    		    return (number/1048576).toFixed(1) + 'MB';
    		  }
    		}
    	
    </script>
    
</body>
</html>