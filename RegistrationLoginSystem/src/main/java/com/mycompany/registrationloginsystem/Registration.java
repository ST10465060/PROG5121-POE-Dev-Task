/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginsystem;

/**
 *
 * @author eduan
 */
public class Registration {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellNumber;
    
    
    //Ensure that the username is valid and that it meets the required format
    public boolean checkUserName(String username) {
    
        return username.contains("_") && username.length() <=5;
    
    }
    
    //Verify that the password meets the required complexity standards
    public boolean checkPasswordComplexity(String password) {
    String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
    
    return password.matches(regex);
    }
    
    //South African cell number validation.
    //Regex pattern developed with assistance from Deepseek Chat (2025)
    public boolean checkCellPhoneNumber(String cellNumber) {
    String regex = "^\\+27\\d{9}$";
    
    return cellNumber.matches(regex);
    }
    
    // Create a new user and validate it
    public String registerUser(String firstName, String lastName, String username, String password, String cellNumber) {
    
        //Store first name and last name first
        this.firstName = firstName;
        this.lastName = lastName; 
        
        if (!checkUserName(username)) {
           return "Username is not in the correct format, please ensure that your username contains an underscore and is no more than five characters long";
           
        }
        
        if (!checkPasswordComplexity(password)) {
           return "Password is not in the correct format, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character";
           
        }
        
        if (!checkCellPhoneNumber(cellNumber)) {
           return "Cell phone number incorrectly formatted or does not contain international code";
           
        }
        
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        
        return firstName + " " + lastName + " Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
        }
                
    //Retrieval Methods for the registered users data
    public String getFirstName() {return firstName; }
    public String getLastName() {return lastName;}
    public String getUsername() {return username; }
    public String getPassword() {return password; }
    public String getCellNumber() {return cellNumber; }
    
    
}
//Deepseek Chat. (2025). Response to query about South African cell phone number validation regex [Large language model]. 
//https://www.deepseek.com