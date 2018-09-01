<%-- 
    Document   : Login
    Created on : Aug 15, 2018, 2:18:06 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Egerton University Security department Login Form</title>
    </head>
    <body style="background-color: #394B58">

        <h1 style="font-family: monospace; font-weight: normal; color: white; ">
            EGERTON UNIVERSITY
        </h1>
        <br><br>
        <form style="width: 400px; margin: auto; background-color: #394B58; border-color: #394B58;" name="form" action="LoginServlet" method="post">

            <h2 style=" font-weight: normal; color: white; text-align: center; margin-right: 20px">Security Department</h2>
            <br>
            <table align="center">

                <tr>
                    <td></td>
                    <td style="color: white; font-size: 20px;">Username</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input size="30" type="text" name="username" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="color: white; font-size: 20px;">Password</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input size="30" type="password" name="password" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input style="width: 150px; color: white; align-self: center; background-color: #394B58; border-style: ridge; border-color: white" type="submit" value="Login"></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><div style="color: red; font-style: italic; align-self: center"><a style="color: red" href="forgotpassword.jsp">Forgot Password?</a></div></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </body>
</html>
