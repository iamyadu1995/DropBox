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
    else
    {
        session.removeAttribute("member");
        session.invalidate();//all objects that the session holds are deallocated.
        response.sendRedirect("index.jsp");
    }
    
%>    