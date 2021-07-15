<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<h1>Formulari de registre</h1>
			<c:if test="${message != null}">
				<h2 class="error"><c:out value="${message}" /></h2>
			</c:if>
			<p>
				<a href="/gimcana-tecnologica">&lt; Tornar sense registrar-se</a>
			</p>
			<form:form method="post" modelAttribute="playerRegistrationDTO" action="registry">
				<table>
					<tr>
						<td>Gimcana:</td>
						<td>
							<form:select path="event" items="${events}" itemLabel="fullEventName" itemValue="id">
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Nom del jugador / equip:</td>
						<td>
							<form:input path="name" />
							<form:errors path="name" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>Paraula de pas:</td>
						<td>
							<form:password path="password" />
							<form:errors path="password" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>Repeteix-la:</td>
						<td>
							<form:password path="repeatedPassword" />
							<form:errors path="repeatedPassword" cssClass="error" />
						</td>
					</tr>
					<spring:hasBindErrors name="playerRegistrationDTO">
						<c:forEach var="error" items="${errors.allErrors}">
							<c:catch var="exception"><c:set var="errorField" value="${error.field}" /></c:catch>
							<c:if test="${not empty exception}">
								<tr>
									<td colspan="2" class="error"><spring:message message="${error}" /></td>
								</tr>
							</c:if>
					    </c:forEach>
					</spring:hasBindErrors>
					<tr>
						<td colspan="2">
							<input type="submit" value="Registrar-me" class="boton"/>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>