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
        <h1>Authentication Failed</h1>
        <%-- 
        plan some forget password help
        or
        client ip trace to block the user
        or
        more innovation
        --%>
    </td>
</tr>
</table>
</body>
</html>
