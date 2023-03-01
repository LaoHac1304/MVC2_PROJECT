/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.registration;

import java.io.Serializable;

/**
 *
 * @author simnh
 */
public class RegistrationDTO implements Serializable{
    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, 
            String lastname, boolean role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }

    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    /**
     * @return the lastName
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastName to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    
}
