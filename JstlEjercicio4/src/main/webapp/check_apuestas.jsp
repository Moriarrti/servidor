<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<c:set var="fecha" scope="page">${sessionScope.primitiva.getFecha().toString() }</c:set>
		<h1>Comprueba apuestas del <c:out value="${fecha }"></c:out></h1>
		<fieldset>
			<form method="post" action="ServletCheckApuesta">
				Nombre:  <input type="text" name="nombre"><br>
				Administracion Nº:  <input type="number" name="numAdmin"><br>
				<h4>Introduce 6 números</h4>
				<c:forEach var="i" begin="1" end="6">
					<input type="number" name="num${i}" style="width:50px">
				</c:forEach>
				<br><br>
				<b>Introduce reintegro:</b>
				<input type="number" name="reintegro"><br><br>
				<button type="submit" name="comprobar" value="COMPROBAR">COMPROBAR</button>
				<br><br>
				<c:if test="${param.msg != null }">
					<h4><c:out value="${param.msg }"></c:out></h4>
				</c:if>
			</form>
		</fieldset>
	</fieldset>

</body>
</html>