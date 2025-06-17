/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginsystem;

/**
 *
 * @author eduan
 */

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class MessageManager {
    
    private final List<Message> sentMessages;
    private final List<Message> disregardedMessages;
    private final List<Message> storedMessages;
    private final List<String> messageHashes;
    private final List<String> messageIds;
    
    public MessageManager() {
        
        this.sentMessages = new ArrayList<>();
        this.disregardedMessages = new ArrayList<>();
        this.storedMessages = new ArrayList<>();
        this.messageHashes = new ArrayList<>();
        this.messageIds = new ArrayList<>();
        loadStoredMessages();
    }
    
    //Load stored messages from JSON file
    private void loadStoredMessages() {
        try {
            
            List<Message> loadedMessages = MessageStorage.loadMessages();
            this.storedMessages.addAll(loadedMessages);
            
            for (Message msg : storedMessages) {
                
                if (!messageHashes.contains(msg.getMessageHash())) {
                    messageHashes.add(msg.getMessageHash());
                    messageIds.add(msg.getMessageId());
                }
            }
        } 
        
        catch (IOException e) {
            
        }
    }
    
    //Add message to appropriate array based on action
    public void addMessage(Message message, int action) {
        switch (action) {
            
            //Send
            case 0: sentMessages.add(message);
                break;
            
            //Disregard
            case 1: disregardedMessages.add(message);
                break;
            
            //Store
            case 2: storedMessages.add(message);
                break;
        }
        
        //Add to tracking arrays
        if (!messageHashes.contains(message.getMessageHash())) {
            messageHashes.add(message.getMessageHash());
            messageIds.add(message.getMessageId());
        }
    }
    
    //Display the sender and recipient sent messages
    public String displaySentMessagesInfo() {
        
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }
        
        StringBuilder result = new StringBuilder("Sent Messages:\n");
        
        for (Message msg : sentMessages) {
            result.append("Recipient: ").append(msg.getRecipient()).append("\n");
        }
        
        return result.toString();
    }
    
    //Display the longest sent message
    public String displayLongestMessage() {
        
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }
        
        Message longest = sentMessages.get(0);
        
        for (Message msg : sentMessages) {
            
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        
        return longest.getMessageText();
    }
    
    //Search for message by ID
    public String searchMessageById(String messageId) {
        
        //Search in sent messages
        for (Message msg : sentMessages) {
            
            if (msg.getMessageId().equals(messageId)) {
                
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
        
        //Search in stored messages
        for (Message msg : storedMessages) {
            if (msg.getMessageId().equals(messageId)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
        
        return "Message ID not found.";
    }
    
    //Search messages by recipient
    public String searchMessagesByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        
        // Search sent messages
        for (Message msg : sentMessages) {
            
            if (msg.getRecipient().equals(recipient)) {
                result.append(msg.getMessageText()).append("\n");
                found = true;
            }
        }
        
        //Search stored messages
        for (Message msg : storedMessages) {
            
            if (msg.getRecipient().equals(recipient)) {
                result.append(msg.getMessageText()).append("\n");
                found = true;
            }
        }
        
        return found ? result.toString().trim() : "No messages found for recipient: " + recipient;
    }
    
    //Delete message by hash
    public String deleteMessageByHash(String messageHash) {
        
        //Try to remove from sent messages
        for (int i = 0; i < sentMessages.size(); i++) {
            
            if (sentMessages.get(i).getMessageHash().equals(messageHash)) {
                Message removed = sentMessages.remove(i);
                messageHashes.remove(messageHash);
                messageIds.remove(removed.getMessageId());
                return "Message \"" + removed.getMessageText() + "\" successfully deleted.";
            }
        }
        
        //Try to remove from stored messages
        for (int i = 0; i < storedMessages.size(); i++) {
            
            if (storedMessages.get(i).getMessageHash().equals(messageHash)) {
                Message removed = storedMessages.remove(i);
                messageHashes.remove(messageHash);
                messageIds.remove(removed.getMessageId());
                
            return "Message \"" + removed.getMessageText() + "\" successfully deleted.";
            }
        }
        
        return "Message hash not found.";
    }
    
    //Display full report of sent messages
    public String displayMessageReport() {
        
        if (sentMessages.isEmpty()) {
            
        return "No sent messages to display.";
        }
        
        StringBuilder report = new StringBuilder("MESSAGE REPORT\n");
        report.append("================\n\n");
        
        for (Message msg : sentMessages) {
            report.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
            report.append("Recipient: ").append(msg.getRecipient()).append("\n");
            report.append("Message: ").append(msg.getMessageText()).append("\n");
            report.append("----------------\n");
        }
        
        return report.toString();
    }
    
    //Getters for testing
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIds() { return messageIds; }
}
