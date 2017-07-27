<%
    gol.Member member = null;
    try
    {
        member = (gol.Member) session.getAttribute("member");
    }
    catch(Exception ex)
    {
        System.out.println("**** "+ ex );
    }
    
    if(member == null)
    {
        response.sendRedirect("login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel ="stylesheet" href="styleIt.css"/>
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
        <jsp:include page="navBarMember.jsp"/>
    </td>
</tr>

<tr>
    <td>WELCOME </td>
</tr>
<tr>
    <td><h2><a href = Upload>Upload Here</a></h2></td>
</tr>
<tr>
    <td><h2><a href="Download">Download Here</a></h2></td>
</tr>

</table>
</body>
</html>
