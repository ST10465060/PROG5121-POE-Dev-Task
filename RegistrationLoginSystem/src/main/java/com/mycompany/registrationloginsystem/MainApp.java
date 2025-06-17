package com.mycompany.registrationloginsystem;

import javax.swing.JOptionPane;
import java.util.Scanner;


public class MainApp {
    
    private static final MessageManager messageManager = new MessageManager();
    private static int totalMessages = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();
        Login login = new Login();
        
        //Registration process
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
        
        //Proceed when successfully registered
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
            
            //After successful login show the QuickChat menu
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
                
                //Displays the main menu options to the user
                "Welcome to QuickChat\n\n" +
                "1) Send Messages\n" +
                "2) Show recently sent messages\n" +
                "3) Display longest message\n" +
                "4) Search message by ID\n" +
                "5) Search message by recipient\n" +
                "6) Delete message by hash\n" +
                "7) Display message report\n" +
                "8) Quit\n\n" +        
                "Enter your choice:");

            //Processes the users choices
            switch(input) {
                case "1": sendMessages();
                    break;
                
                case "2": JOptionPane.showMessageDialog(null, messageManager.displaySentMessagesInfo());
                    break;
                
                case "3": JOptionPane.showMessageDialog(null, "Longest message:\n" + messageManager.displaySentMessagesInfo());
                    break;
                
                case "4": searchMessageById();
                    break;
                    
                case "5": searchMessagesByRecipient();
                    break;
                    
                case "6": deleteMessageByHash();
                    break;
                    
                case "7": JOptionPane.showMessageDialog(null, messageManager.displayMessageReport());
                    break;
                    
                case "8": running = false;
                    break;                
                
                default: JOptionPane.showMessageDialog(null, "Invalid option");
                    
            }
        }
    }

    //Handles the entire message sending process
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

                //Show message options
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
    
    //Search for a message by using it's ID
    private static void searchMessageById() {
    String messageId = JOptionPane.showInputDialog("Enter Message ID:");
    if (messageId != null && !messageId.trim().isEmpty()) {
        String result = messageManager.searchMessageById(messageId);
        JOptionPane.showMessageDialog(null, result);
    }
}

    //Search all messages that were sent to a specific recipient
    private static void searchMessagesByRecipient() {
    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
    if (recipient != null && !recipient.trim().isEmpty()) {
        String result = messageManager.searchMessagesByRecipient(recipient);
        JOptionPane.showMessageDialog(null, result);
    }
}

    //Delete a message by using it's unique hash
    private static void deleteMessageByHash() {
    String messageHash = JOptionPane.showInputDialog("Enter Message Hash:");
    if (messageHash != null && !messageHash.trim().isEmpty()) {
        String result = messageManager.deleteMessageByHash(messageHash);
        JOptionPane.showMessageDialog(null, result);
    }
}

    
    //User decides what to do with a message
    private static void handleMessageChoice(int choice, Message message) {
        
    //Add message to the appropriate array in MessageManager    
    messageManager.addMessage(message, choice);
        
        switch(choice) {
            
            //Send
            case 0: message.displayMessage();
                    JOptionPane.showMessageDialog(null, "Message successfully sent.");
                    break;
            
            //Disregard
            case 1: JOptionPane.showMessageDialog(null, "Press 0 to delete message.");
                    break;
            
            //Store (JSON)
            case 2: message.storeMessage();
                JOptionPane.showMessageDialog(null, "Message successfully stored.");
                break;
        }
    }
}
