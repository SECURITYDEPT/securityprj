<%-- 
    Document   : forgotpassword
    Created on : Aug 17, 2018, 12:20:34 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resource/css/main.css" />
    </head>
    <body>
        <form id="register-form" role="form" method="post" 
              action="mymail.jsp">
            <h3>Enter Your Email Below</h3>
            <input id="email" name="email" placeholder="Email address" type="email" required autofocus>
            <input name="recover-submit" value="Get Password" type="submit">
        </form>
    </body>
</html>
