package com.login.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.login.bean.LoginBean;
import com.login.util.DBConnection;

public class LoginDao {

    public String authenticateUser(LoginBean loginBean) {
        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";

        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select id,password,category from user");

            while (resultSet.next()) {
                userNameDB = resultSet.getString("id");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("category");

                if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin")) {
                    return "Admin_Role";
                } else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("receptionist")) {
                    return "Receptionist";
                } else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("officer")) {
                    return "Officer";
                }
            }
            System.out.println(userNameDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials!";
    }
}
