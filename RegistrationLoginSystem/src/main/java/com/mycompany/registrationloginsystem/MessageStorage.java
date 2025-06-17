/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *Handles JSON storage of messages.
 *Adapted from Deepseek Chat (2025).
 */

public class MessageStorage {
    private static final String FILE_PATH = "messages.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Save messages to JSON file
    public static void storeMessage(Message message) throws IOException {
        List<Message> messages = loadMessages();
        messages.add(message);
        mapper.writeValue(new File(FILE_PATH), messages);
    }

    // Load messages from JSON file
    public static List<Message> loadMessages() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(file, 
            mapper.getTypeFactory().constructCollectionType(List.class, Message.class));
    }
}
//Deepseek Chat (2025)
//https://www.deepseek.com