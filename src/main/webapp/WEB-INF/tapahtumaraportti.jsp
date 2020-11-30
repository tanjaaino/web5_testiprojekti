<%@ page language="java" contentType="text/html; utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8" />

<title>Tapahtumaraportti</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />

</head>
<body>

	<h1>Tapahtumaraportti</h1>
	<p><c:out value="${viesti}" /><p>
	<p><a href="/listaa-asiakkaat">Asiakkaat</a></p>
</body>
</html>