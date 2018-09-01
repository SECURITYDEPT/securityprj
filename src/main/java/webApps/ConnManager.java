/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Buluma
 */
public class ConnManager {
       
    //database url
    private static String url = "jdbc:mysql://localhost:3306/security";
    
    //database driver name
    private static String driverName = "com.mysql.jdbc.Driver";
    
    //database user name
    private static String userName = "root";
    
    //database password
    private static  String passWord = "HIGHskull";
    
    //database connection
    private static Connection con ;
    private static String urlString;
    
    //create the connection to the database
    public static Connection getConnection() throws SQLException{
        
        try{
            Class.forName(driverName);//load the driver 
            try{
                con = DriverManager.getConnection(url, userName, passWord);//make connection to the database
            }catch (SQLException e){
                System.out.print("Failed to connect");//Failure message in connection
            }
        }catch (ClassNotFoundException e){
            System.out.println("Driver not Found");//Failure message in loading driver
        }
        return con;
    }
    
}
