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
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * This is the complaints registered class
 */
@ManagedBean(name = "complaint")
@RequestScoped
public class Complaint {

    private String oBNumber;//complaint OB number
    private String complainantId;//complainant Regristration Number
    private String complainantFName;//complainant first name
    private String complainantSName;//complainant second name
    private String complainantEmail;//complainant name
    private String details;//The complaint's details
    private Date dateRecorded;//date the complaint was recorded
    private String comPhoneN;//complainant's phone number
    private String status;//The current state of the complaint
    private String category;//classification of the complaint
    
    private PreparedStatement  pState,  compDisp ;
    private String choice;//selected category inout
    
    private String query;

    private ResultSet rSet;
    private String gender;//complainant gender
    private String[] titles;


   
    /*
    The getters methods 
     */
    private String officer;
    private String remarks;
     private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }
    

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    


    

    public String[] getTitles() {
        return titles;
    }

    
    
    public String getGender() {
        return gender;
    }

    public String getOB() {
        return oBNumber;
    }

    public String getComplainantFName() {
        return complainantFName;
    }

    public void setComplainantFName(String complainantFName) {
        this.complainantFName = complainantFName;
    }

    public String getCompId() {
        return complainantId;
    }

    public String getDetails() {
        return details;
    }

    public String getComplainantEmail() {
        return complainantEmail;
    }

    public void setComplainantEmail(String complainantEmail) {
        this.complainantEmail = complainantEmail;
    }

    public String getComplainantSName() {
        return complainantSName;
    }

    public void setComplainantSName(String complainantSName) {
        this.complainantSName = complainantSName;
    }

    public Date getDate() {
        return dateRecorded;
    }

    public String getPhoneNum() {
        return comPhoneN;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getChoice() {
        return choice;
    }
   

   
    
    /* 
    The setter methods
     */
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOB(String oB) {
        this.oBNumber = oB;
    }

    public void setCompId(String iD) {
        this.complainantId = iD;
    }

    public void setDetails(String deTails) {
        this.details = deTails;
    }

    public void setPhoneNum(String phone) {
        this.comPhoneN = phone;
    }

    public void setStatus(String state) {
        this.status = state;
    }

    public void setDate(Date dat) {
        this.dateRecorded = dat;
    }

    public void setCategory(String cat) {
        this.category = cat;
    }

    public void setChoice(String choiCe) {
        this.choice = choiCe;
    }
     

    //Initialise the connection to the database
    public void initialisedb(String sqlQuery) throws SQLException {

        pState = (ConnManager.getConnection()).prepareStatement(sqlQuery);//get database connection and pass the query

    }

    //update the complaint details in the database when saved
    public String createComplaint() throws SQLException {
       
        
        
        status = "unresolved";//set the status default value to unresolved

        //Prepared statement to insert values to the table
        query = "insert into cases("
                + " fname, sname, regno, gender, email, phone, category, details,"
                + "dated, status) values(  ?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), ?)";

        initialisedb(query);//initiliase database with the query above

     
        
        pState.setString(1, complainantFName);
        pState.setString(2, complainantSName);
        pState.setString(3, (complainantId.toUpperCase()));
        pState.setString(4, gender);
        pState.setString(5, complainantEmail);
        pState.setString(6, comPhoneN);
        pState.setString(7, category);
        pState.setString(8, details);
        pState.setString(9, status);
        
        pState.executeUpdate();

        return "ViewComplaints.xhtml"; //Return the viewComplaints page for complaints viewing
    }


    //Returns the complaints fetched from the database
    public void officerName() throws SQLException {

       query = "select * from user where category = 'officer'";
       initialisedb(query);
       
       rSet = pState.executeQuery();
       
       ArrayList<String> list = new ArrayList<> ();
       while(rSet.next()){
           list.add(rSet.getString(1));
       }
           titles = new String[list.size()];
           list.toArray(titles);
       
    }
     public String resolveCase(String id) throws SQLException {

        query = "update cases set status = 'resolved' where id = ? ";//SQL query to delete the specified element from the table Complaints
        initialisedb(query);

        pState.setString(1, id);
        pState.executeUpdate();
        
        return "/Admin/ViewCases.xhtml?faces-redirect=true";
        
           
    }
      public void allocate(int id) throws SQLException {
        
       
        
         
        query = "insert into allocation( dated, caseid, officerId, remarks) values(CURDATE(), ?, ?, ?)  ";

        initialisedb(query);
        pState.setInt(1, id);
        pState.setString(2, officer);
        pState.setString(3, remarks);
        pState.executeUpdate();

    }
       
     

    }
    
    
    

  


