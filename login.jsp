<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" href="styleIt.css"/>
<script>
var req, res;    
function authenticate(uName)
{
    req = new XMLHttpRequest();
    var url = "isUserNameAvailable.jsp?uname="+ uName;
    req.open("GET", url, false);//synchronous
    req.onreadystatechange = serverResponse;
    req.send();//GO
}

function serverResponse()
{
    if(req.readyState == 4)//response ready
    {
        if(req.status == 200)//OK
        {
            res = eval(req.responseText);
        }
        else
        {
            res = 0;
        }
    }
}

function checkUserName()
{
    var a = document.getElementById("idUserName").value;
    authenticate(a);

    if(res == 1)
    {
        setError('divUserName', 'Invalid User Name');
    }
    
}

function setError(divId, message)
{
    var x = "<b>" + message + "</b>";
    document.getElementById(divId).innerHTML = x;
}
    
function check()
{
    var a, b;
    a = document.getElementById("idUserName").value;
    b = document.getElementById("idPassword").value;
    
    if(a== "")
    {
        setError('divUserName', 'User Name Missing');
        return false;
    }
    if(b== "")
    {
        setError('divPassword', 'Password Missing');
        return false;
    }
    if(res == 1)
    {
        setError('divUserName', 'Invalid UserName');
        return false;
        
    }
    return true;
}

function clearDiv(divId)
{
    document.getElementById(divId).innerHTML = "";
}

</script>
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
    <table>
        <tr>
            <td width="50%" >
                <fieldset>
                    <legend> INFORMATION</legend>
                    <ul>
                        <li> Go to Dropbox and set up an account. </li>
                        <li>Create folders in Dropbox, and then create symbolic links to those folders on your PC.</li>
                        <li>If your hard drive is especially small, make room on it by moving some of your files to Dropbox. </li>
                        <li>Many of us store the files and folders for active projects on the desktop. Put them in Dropbox instead.</li>
                        <li>Scan important personal documents—your passport, driver’s license,and store the scans in Dropbox</li>
                        <li>you can put the most important folders there</li>
                        <li>Remember, you can upgrade from the 2GB that Dropbox gives you for free to 50GB [$10 per month] paid accounts</li>
                        
                    </ul>
                    <h3>Add an extra layer of protection to Dropbox’s security by creating an encrypted container within your Dropbox folder</h3>
                </fieldset>
            </td>
            <td>
                <fieldset>
                    <legend> LOGIN</legend>
                    <table>
                        <form name="frmLogin"  method="post" action="authenticateUser.jsp" onsubmit="return check()"> 
                            <tr>
                                <td>User Name</td>
                                <td><input type ="text" name="txtUserName" id="idUserName" onblur="checkUserName()" onfocus="clearDiv('divUserName')" /></td>
                                <td><div id="divUserName"></div></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type ="password" name="txtPassword" id="idPassword" onfocus="clearDiv('divPassword')" /></td>
                                <td><div id="divPassword"></div></td>
                            </tr>
                            
                            <tr>
                                <td>&nbsp;</td>
                                <td><input type ="submit" name="bttnSubmit" value ="Login" /></td>
                                <td>&nbsp;</td>
                            </tr>
                        </form>
                    </table>
                </fieldset>
                
            </td>
            
            
        </tr>
        
    </table>
</tr>
</table>
</body>
</html>
