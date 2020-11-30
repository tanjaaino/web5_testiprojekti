<!DOCTYPE html>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8" />

<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Lisää Asiakas</title>
<style type="text/css">
label {
	display: block;
	width: 8em;
	float: left;
}
</style>
</head>

<body>
	<h1>Lisää Asiakas</h1>
	<form action="/lisaa-uusiasiakas" method="post">

		<p>
			<label>Etunimi:</label> <input type="text" value="" name="etunimi"size="50" />
		</p>
		<p>
			<label>Sukunimi:</label> <input type="text" value="" name="sukunimi" size="50" />
		</p>
		<p>
			<label>Syntymävuosi:</label> <input type="text" value="" name="syntymavuosi" size="50" />
		</p>
		<p>
			<label>Sukupuoli(M/N):</label> <input type="text" value="" name="sukupuoli" size="50" />
		</p>
		<p>
			<label>Katuosoite:</label> <input type="text" value="" name="katuosoite" size="50" />
		</p>
		<p>
			<label>Postinumero:</label> <input type="text" value="" name="postinro" size="50" />
		</p>
		<p>
			<label>Email:</label> <input type="text" value="" name="email" size="50" />
		</p>
		<p>
			<label>Bonuspisteet:</label> <input type="text" value="" name="bonuspisteet" size="50" />
		</p>
		<p>
		<span class="btn"><a href="/listaa-asiakkaat" >Peruuta</a></span>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		</p>
	</form>

</body>
</html>