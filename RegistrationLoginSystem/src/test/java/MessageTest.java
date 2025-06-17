/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.registrationloginsystem.Message;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/*import org.junit.jupiter.api.BeforeAll;*/

/**
 *
 * @author eduan
 */


public class MessageTest {
    private Message message1;
    private Message message2;

    @BeforeEach
    void setUp() {
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight", 1);
        message2 = new Message("08575975889", "Hi Keegan, did you receive the payment?", 2);
    }

    //==== TEST 1: Message Length Validation ====
    @Test
    void testMessageLength_Success() {
        String result = message1.checkMessageLength();  
        assertEquals("Message ready to send.", result);
    }

    @Test
    void testMessageLength_Failure() {
        String longMessage = "A".repeat(251);
        message1.setMessage(longMessage);
        String result = message1.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1, please reduce size.", result);
    }

    //==== TEST 2: Recipient Number Format ====
    @Test
    void testRecipientNumber_Success() {
        String result = message1.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    void testRecipientNumber_Failure() {
        String result = message2.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    //==== TEST 3: Message Hash Generation ====
    @Test
    void testMessageHash() {
        String hash = message1.createMessageHash();
        assertTrue(hash.matches("\\d{2}:1:[A-Z]+[A-Z]+")); // Validates format like "00:1:HITONIGHT"
    }

    //==== TEST 4: Message ID Generation ====
    @Test
    void testMessageID() {
        assertNotNull(message1.getMessageID());
        assertEquals(10, message1.getMessageID().length());
    }

    //==== TEST 5: Message Actions ====
    @Test
    void testMessageSent() {
        assertEquals("Message successfully sent.", message1.sentMessage(0));
    }

    @Test
    void testMessageDiscarded() {
        assertEquals("Press 0 to delete message.", message2.sentMessage(1));
    }

    @Test
    void testMessageStored() {
        assertEquals("Message successfully stored.", message1.sentMessage(2));
    }
}