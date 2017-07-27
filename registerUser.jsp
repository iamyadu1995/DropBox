<jsp:useBean id="usr" scope="page" class="gol.User"/>
<jsp:setProperty name="usr"  property="*"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body background="images/back.png">
<table width="100%">
<tr>
    <td>
        <jsp:include page="titleBar.jsp"/>
    </td>
</tr>
<tr>
    <td>
        <jsp:include page="navBar.jsp"/>
    </td>
</tr>
<tr>
    <td>
    <%
        if(usr.registerUser())
        {
            out.println("<h1>User Registered Successfully</h1>");
            out.println("<h2><a href = login.jsp>Login</a> to start share your DOCUMENTS</h1>");
        }
        else
        {
            out.println("<h1>User Registered Failed</h1>");
        }
    %>
    </td>
</tr>
</table>
</body>
</html>
