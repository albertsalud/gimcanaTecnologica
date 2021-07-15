<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Enhorabona, t'has enregistrat correctament!</h1>
			<p>
				Només falta esperar que arribi el dia de la gimcana. Ens posarem en contacte amb tu per a indicar-te 
				el lloc d'inici (punt de control).
			</p>
			<p>
				<a href="/gimcana-tecnologica">&lt; Torna a la pàgina principal</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>