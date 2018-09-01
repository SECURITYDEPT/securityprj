/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApps;


import static java.rmi.server.LogStream.log;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author luganu
 */
@FacesValidator("userNameValidator")

public class UserNameValidator implements Validator{
    private PreparedStatement pState;
    private String query;
    private ResultSet rSet;
    private String name;
    private  String newId;
    
   public void initialisedb(String query) throws SQLException {

        pState = (ConnManager.getConnection()).prepareStatement(query);

    }
   
   @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage msg = new FacesMessage("User already exists");
        log("validating submitted id--" +value.toString());
        try {
            getValue(value);
        } catch (SQLException ex) {
            Logger.getLogger(UserNameValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(newId.equals(name)){
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        
        }
    }
        
    public void getValue(Object value) throws SQLException{
            query = "select * from user where id = ?";
        initialisedb(query);
        newId = value.toString();
        pState.setString(1, newId);
        rSet = pState.executeQuery();
        
        if(rSet.next()){
            name = rSet.getString( 1);
        }
            
        }
        
        
        
        
        
        
        
        
    }
    

