<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Web Demo</title>
    <meta charset='utf-8'>
    <meta http-equiv = "Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/ico" href="<c:url value="/img/favicon.ico"/>" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"  integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/aaf6652f81.js"></script>
</head>
<body>
	<main>
        <div class="container py-5">
            <div class="card col-md-10 col-lg-7">
                <div class="card-body text-center">
                    <h1 class="card-title">
                    	<a href="<c:url value="/"/>">Web Demo</a>
                    </h1>
                    <c:url value="/img/avatar/${avatar}" var="img"/>
                    <img class="rounded-circle mt-2" src="${img}" width="150" height="150" alt="${avatar}"/>
                    <!-- AVATAR FORM -->
                    <div class="row">
                        <div class="col-10">
                            <form id="changeAvatarForm" class="d-flex" action="change" method="post">
                                <div class="col-4">
                                    <label class="col-form-label"><b>Elegir Avatar</b></label>
                                 </div>
                                <div class="col">
                                    <select id="changeAvatar" class="form-select" name="avatar">
                                        <c:forEach var="imagen" items="${imagenes}">
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
                        </div>
                        <div class="col-2">
                            <form id="deleteAvatarForm" action="delete" method="post">
                                <input type="hidden" name="imagen" value="${avatar}"/>
                                <button type="submit" class="btn btn-small btn-danger"><i class="fas fa-eraser"></i></button>
                            </form>
                        </div>
                    </div>
                    <!-- /AVATAR FORM -->
                    <!-- UPLOAD FORM -->
                    <div class="row">
                        <form id="uploadForm" action="upload" method="post" enctype="multipart/form-data">
                            <div class="col-10 ps-3">
                                <label class="form-label"><b>Subir imagen</b></label>
                                <input id="uploadAvatar" class="form-control" type="file"  name="image" accept="image/*">
                            </div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-small btn-primary"><i class="fas fa-upload"></i></button>
                            </div>
                        </form>
                    </div>
                    <!-- /UPLOAD FORM -->
                    <!-- FEEDBACK MESSAGE -->
                    <div id="feedback" class="row m-0 p-0">
						<c:if test="${not empty mensaje}">
							<div class="alert ${bsClass} alert-dismissible fade show my-2" role="alert">
								${mensaje}
							  	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
						</c:if>
					</div>
					<div class="row">
					<!-- /FEEDBACK MESSAGE -->
                    <!-- PREVIEW-->
                    <div class="row my-3">
                        <label id="preview"></label>
                    </div>
                    <!-- /PREVIEW-->
                </div>
            </div>
        </div>
    </main>
    <footer class="mb-5">
        <div class="container text-center">
            <p>WebDemo</p>
            <small>Creado por <a href="#">Ariel Armijo</a></small>
        </div>
    </footer>
    
    <script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
</body>
</html>