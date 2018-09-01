/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author luganu
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {

    List<User> list;
    private PreparedStatement pState = null;
    private ResultSet rSet;
    private String query;
    private String choice;
    
    public List<User> getList(){
    return list;    
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
    
     public void initialisedb(String sqlQuery) throws SQLException {
        pState = (ConnManager.getConnection()).prepareStatement(sqlQuery);
    }
        
   
    
    public ArrayList<User> getUserList() throws SQLException{
        ArrayList<User> userList = new ArrayList <>();
        
         
        if(choice == null){
         query = "select * from user order by dated";
        initialisedb(query);
        rSet = pState.executeQuery();
        }
       else if(choice != null){
          query = "select * from user where id = ?";
        initialisedb(query);  
        pState.setString(1, choice);
       rSet = pState.executeQuery();
       }
       
        
        
        while(rSet.next())
        {
            User usr = new User();
            
            usr.setId(rSet.getString(   1 ));
            usr.setFname(rSet.getString(   2));
            usr.setSname(rSet.getString(   3));
             usr.setEmail(rSet.getString(   4));
            usr.setCategory(rSet.getString(   5));
            usr.setGender(rSet.getString(    6));
           
            usr.setPhone(rSet.getString(   7));
            usr.setDateCreated(rSet.getDate(   9));
            
            userList.add(usr);
            
          
                
            
        }
        
        
       
    return userList;
        
    }
    
   public void deleteUserB(String id) throws SQLException {
     
    User usr = new User();
    
    usr.deleteUser(id);
     
    }
    
}
    
    
    
    
    

