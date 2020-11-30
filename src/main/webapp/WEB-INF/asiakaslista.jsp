<%@ page language="java" contentType="text/html; utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8" />
<title>Asiakkaat</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>
<body>
	<h1>Asiakkaat</h1>
	<a href="/lisaa-uusiasiakas">Lisää Asiakas</a>
	<table class="table table_striped" >
		<tr>
			<th>Asiakastunnus</th>
			<th>Etunimi</th>
			<th>Sukunimi</th>
			<th>Syntymävuosi</th>
			<th>Sukupuoli</th>
			<th>Katuosoite</th>
			<th>Postinumero</th>
			<th>Email</th>
			<th>Bonuspisteet</th>
			<th>&nbsp;</th>
		</tr>
		<%--  tämä on jsp-komentti --%>
		<%--  ${asiakkaat} viittaa keyword-arvolla request-olion Map-tietorakenteessa olevaan asiakaslistaan(ArrayList) --%>
		<c:forEach items="${asiakkaat}" var="asiakas">
			
			<tr>
				<td><c:out value="${asiakas.asiakastunnus}" /></td>  <%-- lyhennysmerkintä metodikutsulle ${asiakas.getAsiakastunnus()} --%>
				<td><c:out value="${asiakas.etunimi}" /></td> 
				<td><c:out value="${asiakas.sukunimi}" /></td> 
				<td><c:out value="${asiakas.syntymavuosi}" /></td>
				<td><c:out value="${asiakas.sukupuoli}" /></td> 
				<td><c:out value="${asiakas.katuosoite}" /></td>
				<td><c:out value="${asiakas.postinro}" /></td> 
				<td><c:out value="${asiakas.email}" /></td> 
				<td><c:out value="${asiakas.bonuspisteet}" /></td>
				<td><a href="/poista-asiakas?asiakasid=<c:out value='${asiakas.asiakastunnus}'/>">Poista</a></td> <%-- asiakaskohtainen poisto-linkki --%>
			</tr>
		</c:forEach>
	</table>
</body>
</html>