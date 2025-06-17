/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.registrationloginsystem.Message;
import com.mycompany.registrationloginsystem.MessageManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 *
 * @author eduan
 */
public class MessageManagerTest {
    private MessageManager messageManager;
    private Message message1, message2, message3, message4, message5;
    
    @BeforeEach
    void setUp() {
        messageManager = new MessageManager();
        
        //Test data 
        message1 = new Message("+27834557896", "Did you get the cake?", 1);
        message2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        message3 = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        message4 = new Message("0838884567", "It is dinner time !", 4);
        message5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        
        //Add messages with flags
        messageManager.addMessage(message1, 0); //Sent
        messageManager.addMessage(message2, 2); //Stored
        messageManager.addMessage(message3, 1); //Disregard
        messageManager.addMessage(message4, 0); //Sent
        messageManager.addMessage(message5, 2); //Stored
    }
    
    @Test
    void testSentMessagesArrayPopulated() {
        List<Message> sentMessages = messageManager.getSentMessages();
        assertEquals(2, sentMessages.size());
        assertEquals("Did you get the cake?", sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time !", sentMessages.get(1).getMessageText());
    }
    
    @Test
    void testDisplayLongestMessage() {
        String longest = messageManager.displayLongestMessage();
        assertEquals("Did you get the cake?", longest);
    }
    
    @Test
    void testSearchMessageById() {
        String result = messageManager.searchMessageById(message4.getMessageId());
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time !"));
    }
    
    @Test
    void testSearchMessagesByRecipient() {
        String result = messageManager.searchMessagesByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    @Test
    void testDeleteMessageByHash() {
        String result = messageManager.deleteMessageByHash(message2.getMessageHash());
        assertTrue(result.contains("successfully deleted"));
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time"));
    }
    
    @Test
    void testDisplayMessageReport() {
        String report = messageManager.displayMessageReport();
        assertTrue(report.contains("MESSAGE REPORT"));
        assertTrue(report.contains(message1.getMessageHash()));
        assertTrue(report.contains(message4.getMessageHash()));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time !"));
    }
}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

