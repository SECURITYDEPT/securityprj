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
import java.util.Map;
//import javax.inject.Named;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.faces.bean.SessionScoped;

/**
 *
 * @author luganu
 */
@ManagedBean(name = "compaintBean")
@SessionScoped
public class CompaintBean {

    private PreparedStatement pState;
    private String officer;
    private String choice;
    private String query;
    private ResultSet rSet;
    private String details;
    private int id;
  

    private String remarks;
    public String viewId() throws SQLException {
        //FacesContext fc = FacesContext.getCurrentInstance();
        //this.id = getIdParam(fc);
        //this.caseid = id;
        
        
        return "Allocation.xhtml";

        
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    

    //Initilaise the database connection 
    public void initialisedb(String sqlQuery) throws SQLException {
        pState = (ConnManager.getConnection()).prepareStatement(sqlQuery);
    }

    public ArrayList<Complaint> getCompList() throws SQLException {
        ArrayList<Complaint> caseList = new ArrayList<>();

        if (choice == null) {
            query = "select * from cases";
            initialisedb(query);
            rSet = pState.executeQuery();
        } else if (choice != null) {
            query = "select * from cases where id = ?";
            initialisedb(query);
            pState.setString(1, choice);
            rSet = pState.executeQuery();
        }

        while (rSet.next()) {
            Complaint comp = new Complaint();

            comp.setOB(rSet.getString(1));
            comp.setComplainantFName(rSet.getString(2));
            comp.setComplainantSName(rSet.getString(3));
            comp.setCompId(rSet.getString(4));
            comp.setGender(rSet.getString(5));
            comp.setComplainantEmail(rSet.getString(6));
            comp.setPhoneNum(rSet.getString(7));
            comp.setCategory(rSet.getString(8));
            comp.setDetails(rSet.getString(9));
            comp.setDate(rSet.getDate(10));
            comp.setStatus(rSet.getString(11));

            caseList.add(comp);

        }

        return caseList;

    }

    public String viewDetails( ) {
        FacesContext fc = FacesContext.getCurrentInstance();
        this.details = getDetailsParam(fc);

        return "/User/ViewDetails.xhtml";
    }

    public String getDetailsParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("details");

    }
   

    

   /* public String getIdParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("OB");
    }*/

   
   
}
