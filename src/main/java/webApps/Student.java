/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author luganu
 */
@Named(value = "student")
@ApplicationScoped
public class Student {

    private String Id ;
    private PreparedStatement pState;
    private String query;

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void initialisedb(String query) throws SQLException {

        pState = (ConnManager.getConnection()).prepareStatement(query);

    }

    public ResultSet showStudents() throws SQLException {
        
        query = "select reg, name, phone, resident, hostel, room, course, status, faculty from student where reg = ?";
        initialisedb(query);
        
        pState.setString(1, Id);
        ResultSet rSet = pState.executeQuery();

        return rSet;

    }

}
