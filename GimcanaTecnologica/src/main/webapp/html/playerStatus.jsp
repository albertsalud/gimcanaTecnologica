<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Punt de control</h1>
			<c:if test="${resultMessage != null }">
				<p>${resultMessage}</p>
			</c:if>
			<p id="secretWord">De moment has trobat: <b>${charactersFound}</b></p>
			<p>
				${descriptionMessage}
			</p>
		</div>
	</div>
</body>
</html>