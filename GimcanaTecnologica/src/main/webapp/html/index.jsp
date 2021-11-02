<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Gimcana Tecnològica</h1>
			<p>
				Des de l'any <strong>2.015</strong> organitzem a <strong>Sant Climent de Llobregat</strong> un divertit joc que vam batejar com a 
				<strong>Gimcana Tecnològica</strong>. L'objectiu d'aquest joc és trovar la paraula oculta abans que la resta de participants.
			</p>
			<p>Per fer-ho, necessitareu el següent material:</p>
			<ul>
				<li>Dispossitiu mòvil amb accès a dades (Internet) i càmera integrada.</li>
				<li>Software per a llegir codis QR.</li>
			</ul>
			<p>
				La prova consisteix en trovar una paraula oculta visitant diverses ubicacions de <strong>Sant Climent</strong>. Mitjançant
				una pista inicial, en forma d'endevinalla, haureu de decidir quin és el següent lloc del poble a on us heu de dirigir. Quan arribeu,
				<strong>cerqueu un codi QR i escanejeu-lo</strong> amb el vostre dispossitiu. Si és el lloc correcte, us dirà una lletra de la vostra paraula, i 
				us proposarà una altra endevinalla per aconseguir la següent. Si no és el lloc correcte, <strong>¡mala sort!</strong> haureu de tornar a 
				re-llegir la vostra pista amb més atenció. <strong>Però doneu-vos pressa!</strong> Els altres equips estan a la recerca de les seves respectives 
				paraules, també.
			</p>
			<p>
				És un joc entretingut, d'<strong>entre 30 minuts i una hora</strong>, que us permetrà passar una tarda diferent en bona companyia. I, encara que hagueu participat en altres edicions,
				no és problema, ja que les paraules ocultes i els llocs que haureu de visitar canvien cada vegada.
			</p>
			<c:if test="${events.size() > 0 }">
				<p>A continuació us deixem el llistat de les properes gimcanes:</p>
				<table id="data-table" cellpadding="5" cellspacing="0">
					<tr>
						<th>Data</th>
						<th>Nom</th>
						<th>Punt de sortida</th>
					</tr>
					<c:forEach items="${events}" var="currentEvent">
						<tr>
							<td>
								<fmt:formatDate value="${currentEvent.initDate}" pattern="dd/MM/yyyy HH:mm"/>
							</td>
							<td>${currentEvent.name}</td>
							<td>${currentEvent.meetingPoint}</td>
						</tr>
					</c:forEach>
				</table>
				<p>
					Per a participar, ompleneu el <a href="events/registry">formulari de registre.</a>
				</p>
			</c:if>
			<p>Si ets un administrador del joc, <a href="admin">accedeix</a> per gestionar les gimcanes.</p>
		</div>
		<div id="tools">
			<c:import url="${applicationScope.webURL}/tools.html" />
		</div>
	</div>
</body>
</html>