<%-- 
    Document   : mymail
    Created on : Aug 17, 2018, 1:03:28 PM
    Author     : root
--%>

<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="mdhash.mdjavahash"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            mdjavahash md = new mdjavahash();
            String smail = request.getParameter("email");
            int profile_id = 0;
            if (smail != null) {
                try {
// Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

// Open a connection
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security", "root", "");

                    Statement stmt = conn.createStatement();

                    String sql1;
                    sql1 = "SELECT  email FROM user WHERE email = '" + smail + "'";

                    ResultSet rs1 = stmt.executeQuery(sql1);

                    if (rs1.first()) {
                        String sql;
                        sql = "SELECT Profile_id FROM user where email='" + smail + "'";
                        ResultSet rs2 = stmt.executeQuery(sql);

                        // Extract data from result set
                        while (rs2.next()) {
                            //Retrieve by column name
                            profile_id = rs2.getInt("Profile_id");
                        }

                        java.sql.Timestamp intime = new java.sql.Timestamp(new java.util.Date().getTime());
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(intime.getTime());
                        cal.add(Calendar.MINUTE, 20);
                        java.sql.Timestamp exptime = new Timestamp(cal.getTime().getTime());

                        int rand_num = (int) (Math.random() * 1000000);
                        String rand = Integer.toString(rand_num);
                        String finale = (rand + "" + intime); // 
                        String hash = md.getHashPass(finale); //hash code

                        String save_hash = "insert into  reset_password (Profile_id, hash_code,exptime, datetime) values(" + profile_id + ", '" + hash + "'  , '" + exptime + "', '" + intime + "'  )";

                        int saved = stmt.executeUpdate(save_hash);
                        if (saved > 0) {
                            String link = "http://localhost:8080/Security/reset_password.jsp";
                            //bhagawat till here, you have fetch email and verified with the email from datbase and retrived password from the db.
                            //-----------------------------------------------
                            String host = "", user = "", pass = "";
                            host = "smtp.gmail.com";
                            user = "abed.kimamo2@gmail.com";
//"email@removed" // email id to send the emails 
                            pass = "kiatu-1."; //Your gmail password 
                            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
                            String to = smail;
                            String from = "abedkimamo@gmail.com";
                            String subject = "Password Reset";
                            String messageText = " Click <a href=" + link + "?key=" + hash + ">Here</a> To Reset your Password.You must reset your password within 20 minutes";//messageString; 
                            String fileAttachment = "";
                            boolean WasEmailSent;
                            boolean sessionDebug = true;
                            Properties props = System.getProperties();
                            props.put("mail.host", host);
                            props.put("mail.transport.protocol.", "smtp");
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.", "true");
                            props.put("mail.smtp.port", "465");
                            props.put("mail.smtp.socketFactory.fallback", "false");
                            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
                            props.setProperty("proxySet", "true");
                            props.setProperty("socksProxyHost", "172.16.63.15");
                            props.setProperty("socksProxyPort", "3128");
                            Session mailSession = Session.getDefaultInstance(props, null);
                            mailSession.setDebug(sessionDebug);
                            Message msg = new MimeMessage(mailSession);
                            msg.setFrom(new InternetAddress(from));
                            InternetAddress[] address = {new InternetAddress(to)};
                            msg.setRecipients(Message.RecipientType.TO, address);
                            msg.setSubject(subject);
                            msg.setContent(messageText, "text/html");
                            Transport transport = mailSession.getTransport("smtp");
                            transport.connect(host, user, pass);
        %>
        <div style="padding: 30px; background-color: grey; 
             color: white; opacity: 1; transition: opacity 0.6s; width:50%; margin: 10% 
             5% 
             15% 20%;">
            <a href="forgotpassword.jsp"> <span style="color: white; 
                                                font-weight: bold; float: right; font-size: 40px; line-height: 35px; cursor: 
                                                pointer; transition: 0.3s;">&times;</span> </a> 
            <h1 style="font-size:30px;">&nbsp;&nbsp; <strong>Check Your Email. Link To 
                    Reset Your Password Is Sent To : <%out.println(" " + smail); %></strong>  
            </h1>
            <center><h2><input type="button" value="OK"><a href="forgotpassword.jsp"></a></h2></center>
        </div>
        <%
                try {
                    transport.sendMessage(msg, msg.getAllRecipients());
                    WasEmailSent = true; // assume it was sent 
                } catch (Exception err) {
                    WasEmailSent = false; // assume it's a fail 
                }
                transport.close();
                //-----------------------------------------------
            }
        } else {
        %>
        <div style="padding: 30px; background-color: grey; 
             color: white; opacity: 1; transition: opacity 0.6s; width:50%; margin: 10% 
             5% 15% 20%;">
            <a href="forgotpassword.jsp"> <span style="color: 
                                                white; font-weight: bold; float: right; font-size: 40px; line-height: 35px; 
                                                cursor: pointer; transition: 0.3s;">&times;</span> </a> 
            <h1 style="font-size:30px;">&nbsp;&nbsp; <strong>There Is No Email As 
                    Such <%out.println(" " + smail); %></strong>Try Again  </h1>
            <center><h2><input type="button" value="OK"><a href="forgotpassword.jsp"></a></h2></center>
        </div>
        <%
                }

                stmt.close();
                rs1.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        } else {
        %>
        <div style="padding: 30px; background-color: grey; 
             color: white; opacity: 1; transition: opacity 0.6s; width:50%; margin: 10% 
             5% 15% 20%;">
            <a href="forgotpassword.jsp"> <span style="color: white; 
                                                font-weight: bold; float: right; font-size: 40px; line-height: 35px; 
                                                cursor: 
                                                pointer; transition: 0.3s;">&times;</span> </a> 
            <h1 style="font-size:30px;">&nbsp;&nbsp; <strong>Please Enter The Valid 
                    Email Address</strong>  </h1>
            <center><h2><input type="button" value="OK"></h2><a href="forgotpassword.jsp"></a></center>
        </div>
        <%
            }
        %> 
    </body>
</html>
