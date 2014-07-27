<%@page import="com.netdimensions.client.User"%>
<%@page import="com.netdimensions.client.Request"%>
<%@page import="com.netdimensions.client.servlet.Servlets"%>
<%
	User user = Servlets.client(request).send(Request.me());
	String title = "Hello, " + user.given() + "!";
%>
<html>
<head>
<title><%=title%></title>
</head>
<body>
	<h1><%=title%></h1>
	<table>
		<tr>
			<th align="right">ID:</th>
			<td><%=user.id()%></td>
		</tr>
		<tr>
			<th align="right">Name:</th>
			<td><%=user.given()%> <%=user.family()%></td>
		</tr>
		<tr>
			<th align="right">Email:</th>
			<td><%=user.email()%></td>
		</tr>
	</table>
</body>
</html>
