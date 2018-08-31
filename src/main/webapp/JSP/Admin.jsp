<%-- 
    Document   : Admin
    Created on : Aug 15, 2018, 2:23:45 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>

        <link rel="stylesheet" href="/resource/css/main.css" />
        <script type="text/javascript" src="/resource/js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/resource/js/fileupload.js"></script>
    </head>
    <body>
    <center><h2>Admin's Home</h2></center>

    Welcome <%=request.getAttribute("userName")%>

    <div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
    <input type="button" value="Upload" onclick="window.location='fileupload.jsp'" >
</body>
</html>
