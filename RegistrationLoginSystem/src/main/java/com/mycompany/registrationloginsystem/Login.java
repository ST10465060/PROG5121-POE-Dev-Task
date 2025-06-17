/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginsystem;

/**
 *
 * @author eduan
 */
public class Login {
   
    private Registration registeredUser;
    
    public Login() {
        this.registeredUser = null;
    }
    
    //Login verification for the registered user data
    public void setRegisteredUser(Registration user) {
         this.registeredUser = user;
    }
    
    //Verify login credentials
    public boolean loginUser(String username, String password) {
          if (registeredUser == null) return false;
          return username.equals(registeredUser.getUsername()) && 
                 password.equals(registeredUser.getPassword());
    }
    
   //Return login status message
   public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            
     // Verify registered user
     if (registeredUser == null) {
     return "Error: No user registration data available";
     
     }
            
     return "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName() + " it is wonderful to see you.";
        
     } else {
            
     return "Username or password incorrect, please try again.";
        
        }
    }
}
