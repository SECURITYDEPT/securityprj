/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApps;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Buluma
 */
@ManagedBean(name = "user")
@RequestScoped
public class User {
    private String Id;
    private String category;
    private String Fname, Sname;
    private String passWord;
    private String phone;
    private String email;
    private String gender;
      private PreparedStatement pState;
    private String newPass, oldPass, confrim, query1;
    private Date dateCreated;
    private ResultSet rSet;
    private String passWord1;
    private String choice;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getPassWord1() {
        return passWord1;
    }

    public void setPassWord1(String passWord1) {
        this.passWord1 = passWord1;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
  
    
  

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfrim() {
        return confrim;
    }

    public void setConfrim(String confrim) {
        this.confrim = confrim;
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
      
   public String deleteUser(String id) throws SQLException{
     
        //database Query
     String query = "delete from user where id = ?";
     
     //pass the query to the database connection method
     initialisedb(query);
     
         pState.setString(1, id);//set the ID to be passed in the query
         pState.executeUpdate();
         
         System.out.println("user deleted successfully");
         pState.close();
         
         return "/ViewUsers.xhtml?faces-redirect=true";
     
    }
   //Reset the password to the user name of the user 
    public String resetPassWord(String id) throws SQLException{
        String query = "update user set password = ? where id = ?";
        initialisedb(query);
        
        pState.setString(1, id);
        pState.setString(2, id);
        pState.executeUpdate();
        pState.close();
        return "/ViewUsers.xhtml?faces-redirect=true";
    }
      public void changePassWord() throws SQLException{    
        Id = "S1";
        
         query1 = "select * from user where id = ?";
        initialisedb(query1);
        
        
        pState.setString(1, Id);
        
         rSet = pState.executeQuery();
       
      
     while(rSet.next()){
       passWord1 = rSet.getString( 8);
     }
        
     
        if((confrim.equals(newPass)) && (oldPass.equals(passWord1))){
        
        String query = "update user set password = ? where id = ?";
        initialisedb(query);
        
        pState.setString(1, newPass);
        pState.setString(2, Id);
        pState.executeUpdate();
        }
        }
       //Method to add new user to the user database
    public String createNewUser() throws SQLException {
        
        passWord = Id; //Initilaise default password to be the user ID
        
        //Database query to add the new User
        String query = "insert into user(id, fname, sname,email, category,"
                + "gender, phone, password, dated) values(?, ?, ?, ? , ?, ?, ?, ?, CURDATE())";
        
        initialisedb(query);//pass query to the database connection method
           
            //Set the values to be passed in the query
            pState.setString(1, Id);
            pState.setString(2, Fname);
            pState.setString(3, Sname);
            pState.setString(4, email);

            pState.setString(5, category);
            pState.setString(6, gender);
            pState.setString(7, phone);
            pState.setString(8, passWord);
            
            pState.executeUpdate();
            return "ViewUsers.xhtml";
        

        }

   
        
    
    
    
    

    
    
}

