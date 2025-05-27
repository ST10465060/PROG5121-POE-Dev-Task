package com.mycompany.registrationloginsystem;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mycompany.registrationloginsystem.Message;
import com.mycompany.registrationloginsystem.Registration;
import com.mycompany.registrationloginsystem.Login;

public class MainApp {
    
    private static List<Message> sentMessages = new ArrayList<>();
    private static int totalMessages = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();
        Login login = new Login();
        
        // Registration process
        System.out.println("----REGISTRATION----");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter your number with a South African country Code (e.g. +27816692255): ");
        String cellNumber = scanner.nextLine();
        
        String regResults = registration.registerUser(firstName, lastName, username, password, cellNumber);
        System.out.println(regResults);
        
        // Proceed when successfully registered
        if (regResults.contains("successful")) {
            login.setRegisteredUser(registration);
            
            // Login process
            System.out.println("\n=== LOGIN ===");
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();
            
            boolean loginStatus = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginStatus));
            
            // After successful login show the QuickChat menu
            if (loginStatus) {
                showQuickChatMenu();
            }
        }
        scanner.close();
    }
    
    private static void showQuickChatMenu() {
        boolean running = true;

        while (running) {
            String input = JOptionPane.showInputDialog(
                "Welcome to QuickChat\n\n" +
                "1) Send Messages\n" +
                "2) Show recently sent messages\n" +
                "3) Quit\n\n" +
                "Enter your choice:");

            switch(input) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
            }
        }
    }

    private static void sendMessages() {
        try {
            int numMessages = Integer.parseInt(JOptionPane.showInputDialog(
                "How many messages would you like to send?"));
            
            for (int i = 0; i < numMessages; i++) {
                totalMessages++;
                String recipient = JOptionPane.showInputDialog(
                    "Enter recipient number (+27 format):");
                String message = JOptionPane.showInputDialog(
                    "Enter your message (max 250 chars):");
                
                if (message.length() > 250) {
                    JOptionPane.showMessageDialog(null, 
                        "Message exceeds 250 characters by " + 
                        (message.length() - 250) + 
                        ", please reduce size.");
                    continue;
                }

                Message newMessage = new Message(recipient, message, totalMessages);

                // Show message options
                String[] options = {"Send Message", "Disregard Message", "Store for Later"}; 
                int choice = JOptionPane.showOptionDialog(null, "Message ready to send. Choose action:", "Message Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

                handleMessageChoice(choice, newMessage);
            }
                                          
            JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
        }
    }

    private static void handleMessageChoice(int choice, Message message) {
        switch(choice) {
            case 0: // Send
                sentMessages.add(message);
                message.displayMessage();
                JOptionPane.showMessageDialog(null, "Message successfully sent.");
                break;
            case 1: // Disregard
                JOptionPane.showMessageDialog(null, "Press 0 to delete message.");
                break;
            case 2: // Store (JSON)
                message.storeMessage();
                JOptionPane.showMessageDialog(null, "Message successfully stored.");
                break;
        }
    }

    public void handleMenuInput(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isInMessageSendingMode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
