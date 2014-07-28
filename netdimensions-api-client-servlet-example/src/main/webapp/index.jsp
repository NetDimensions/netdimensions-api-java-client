<%@page import="com.netdimensions.client.Country"%>
<%@page import="com.netdimensions.client.User"%>
<%@page import="com.netdimensions.client.Request"%>
<%@page import="com.netdimensions.client.servlet.Servlets"%>
<%
	User user = Servlets.client(request).send(Request.me());
	String title = "Hello, " + user.given() + "!";
	Country country = user.country();
%>
<html>
<head>
<title><%=title%></title>
</head>
<body>
	<h1><%=title%></h1>
	<table>
		<tr>
			<th align="right" valign="top">ID:</th>
			<td><%=user.id()%></td>
		</tr>
		<tr>
			<th align="right" valign="top">Name:</th>
			<td><%=user.given()%> <%=user.family()%></td>
		</tr>
		<tr>
			<th align="right" valign="top">Address:</th>
			<td><%=user.address1()%><br><%=user.address2()%><br><%=user.city()%><br><%=user.provinceState()%><br><%=user.postalCodeZip()%><br><%=country == null ? null : (country.name() + " (" + country.code() + ")")%></td>
		</tr>
		<tr>
			<th align="right" valign="top">Email:</th>
			<td><%=user.email()%></td>
		</tr>
	</table>
</body>
</html>
