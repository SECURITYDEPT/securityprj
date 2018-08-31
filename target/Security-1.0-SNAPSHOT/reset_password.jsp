<%-- 
    Document   : reset_password
    Created on : Aug 17, 2018, 12:30:28 PM
    Author     : root
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String hash = (request.getParameter("key"));

            java.sql.Timestamp curtime = new java.sql.Timestamp(new java.util.Date().getTime());

            int profile_id = 0;
            java.sql.Timestamp exptime;

            try {
// Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

// Open a connection
                Connection conn
                          = DriverManager.getConnection("jdbc:mysql://localhost:3306/security", "root","");
                Statement stmt = conn.createStatement();

                String sql = "select profile_id, exptime from reset_password where hash_code ='" + hash + "'  ";

                ResultSet rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    profile_id = rs.getInt("Profile_id");
                    exptime = rs.getTimestamp("exptime");

                    //out.println(exptime+"/"+curtime);
                    if ((curtime).before(exptime)) {
        %>
        <div>
            <form action="update_reset.jsp" method="Post"> 
                <br/><br/>
                <h4>Reset Your Password Here</h4>
                <br> 
                Enter New Password:<input type="password" name="newpassword" placeholder="New Password" required autofocus>
                <br>
                Enter New Password Again:<input type="password" name="confirmpassword" placeholder="New Password Again" required>

                <input type="hidden" name="profile_id" value=<%=profile_id%> />
                <br>
                <button type="submit">Reset Password</button>
            </form>
        </div> <!-- /container -->
        <% } else {
        %>
        <div style="padding: 30px; background-color: 
             grey; color: white; opacity: 1; transition: opacity 0.6s; width:50%; 
             margin: 10% 5% 15% 20%;">
            <a href="forgotpassword.jsp"> <span
                                                style="color: white; font-weight: bold; float: right; font-size: 40px; 
                                                line-height: 35px; cursor: pointer; transition: 0.3s;">&times;</span> 
            </a> 
            <h1 style="font-size:30px;">&nbsp;&nbsp; The Time To Reset 
                Password Has Expired.<br> &nbsp;&nbsp; Try Again </h1>
            <center><h2><input type="button" value="OK"></h2><a href="forgotpassword.jsp"></a></center>
        </div>
        <%
            }
        } else {
        %>
        <div style="padding: 30px; background-color: grey; 
             color: white; opacity: 1; transition: opacity 0.6s; width:50%; margin: 
             10% 5% 15% 20%;">
            <a href="forgotpassword.jsp"> <span style="color: 
                                                white; font-weight: bold; float: right; font-size: 40px; line-height: 
                                                35px; cursor: pointer; transition: 0.3s;">&times;</span> </a> 
            <h1 style="font-size:30px;">&nbsp;&nbsp; The Hash Key DO Not Match. 
                <br/> &nbsp;&nbsp;&nbsp;Try Again!! </h1>
            <center><h2><input type="button" value="OK"></h2><a href="forgotpassword.jsp"></a></center>
        </div>
        <%
                }
                // Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %> 
    </body>
</html>
