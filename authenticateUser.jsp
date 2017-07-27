<jsp:useBean id="usr" scope="page" class="gol.User" />
<jsp:setProperty name="usr" property="*" />

<%
    gol.Member member = usr.authenticateUser();
    if(member != null)
    {//success
        session.setAttribute("member", member);
        response.sendRedirect("userHome.jsp");
    }
    else
    {//failed
        response.sendRedirect("authenticationFailed.jsp");
    }
%>