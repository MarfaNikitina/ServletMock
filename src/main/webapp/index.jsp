<%@ page import="logic.Model" %>
<%--<%@ page import="main.java.logic.Model" %>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Home Page</h2>
Enter User ID (0 - for all user list)
<br/>
All:
<%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
%>
<form method="get" action="getView">
    <label>ID:
    <input type="text" name="id">
    <br/></label>
    <button type="submit">Search</button>
</form>

<a href="addUser.html">Create new user</a>
</body>
</html>
