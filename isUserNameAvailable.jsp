<jsp:useBean id="usr" scope="page" class="gol.User" />

<% 
    String uname = request.getParameter("uname");
    System.out.println(uname);
    String result = usr.isUserNameAvailable(uname);
    out.println(result);
%>