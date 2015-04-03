<%@page import="com.netdimensions.client.Country"%>
<%@page import="com.netdimensions.client.User"%>
<%@page import="com.netdimensions.client.Request"%>
<%@page import="com.netdimensions.client.servlet.Servlets"%>
<%
	User user = Servlets.client(request).send(Request.me());
	String country = user.country() == null ? "Unknown" : (user.country().code() + " - " + user.country().name());
%>
<html>
<head>
<title>About You</title>
</head>
<body>
	<h1>About You</h1>
	<dl>
		<dt>Name</dt>
		<dd><%=user.prefix()%>
			<%=user.given()%>
			<%=user.family()%></dd>
		<dt>Email Address</dt>
		<dd><%=user.email()%></dd>
		<dt>Telephone Number</dt>
		<dd><%=user.phone()%></dd>
		<dt>Address Line 1</dt>
		<dd><%=user.address1()%></dd>
		<dt>Address Line 2</dt>
		<dd><%=user.address2()%></dd>
		<dt>Town Or City</dt>
		<dd><%=user.city()%></dd>
		<dt>State, Province or County</dt>
		<dd><%=user.provinceState()%></dd>
		<dt>Country</dt>
		<dd><%=country%></dd>
		<dt>Post Or Zip Code?</dt>
		<dd><%=user.postalCodeZip()%></dd>
	</dl>
</body>
</html>
