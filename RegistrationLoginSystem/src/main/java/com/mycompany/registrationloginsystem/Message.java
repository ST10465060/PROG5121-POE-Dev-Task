/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginsystem;

/**
 *
 * @author eduan
 */
import java.util.Random;
import javax.swing.JOptionPane;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;

public class Message 
{
  @JsonProperty("messageId") private String messageId;
  @JsonProperty("messageCount") private int messageCount;
  @JsonProperty("recipient") private String recipient;
  @JsonProperty("messageText") private String messageText;
  @JsonProperty("messageHash") private String messageHash;
   
  //Constructor
  public Message(String recipient, String messageText, int messageCount)
  {
     this.messageId = generateMessageId();
     this.recipient = recipient;
     this.messageText = messageText;
     this.messageCount = messageCount;
     this.messageHash = createMessageHash();
  }   

  //Generate a random 10 digit message ID
  private String generateMessageId()
  {
     Random rand = new Random();
     long id = 1000000000L + rand.nextInt(900000000);
     return String.valueOf(id);
  }
  
  //Check message ID lenth
  public boolean checkMessageId() 
  {
      return this.messageId.length() == 10;
  }
  
  //Validate recipient cell number (re-use from Registration class)
  public String checkRecipientCell() {
    if (this.recipient.matches("^\\+27\\d{9}$")) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}
  
  //message hash (format: 00:0:HITHANKS)
  public String createMessageHash() {
    String[] words = this.messageText.split(" ");
    String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
    String lastWord = words.length > 1 ? words[words.length-1].toUpperCase() : firstWord;
    
    return String.format("%s:%d:%s%s", 
        messageId.substring(0, 2), 
        messageCount, 
        firstWord, 
        lastWord);
}
  
  //Display message via JOptionPane
  public void displayMessage(){
  String output = "Message ID: " + messageId + "\n" + 
                  "Message Hash: " + messageHash + "\n" +
                  "Recipient: " + recipient + "\n" +
                  "Message: " + messageText;
  
  JOptionPane.showMessageDialog(null, output);
  }
  
  public void storeMessage() {
        try {
            MessageStorage.storeMessage(this);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }

  //Getters
  public String getMessageId() { return messageId; }
  public String getRecipient() { return recipient; } 
  public String getMessageText() { return messageText; }
  public String getMessageHash() { return messageHash; }
  public int getMessageCount() { return messageCount; }
  public String getMessageID() { return this.messageId; }
  
//method for message length validation
public String checkMessageLength() {
    if (this.messageText.length() <= 250) {
        return "Message ready to send.";
    } else {
        return "Message exceeds 250 characters by " + 
               (this.messageText.length() - 250) + ", please reduce size.";
    }
}

//Setter for message text (needed for testing long messages)
public void setMessage(String message) {
    this.messageText = message;
}

//New method for message actions (Send/Discard/Store)
public String sentMessage(int choice) {
    switch (choice) {
        case 0: return "Message successfully sent.";
        case 1: return "Press 0 to delete message.";
        case 2: return "Message successfully stored.";
        default: return "Invalid option.";
    }
}

}
