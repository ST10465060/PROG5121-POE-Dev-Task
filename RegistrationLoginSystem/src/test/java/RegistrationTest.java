/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.registrationloginsystem.Registration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author eduan
 */
public class RegistrationTest {
    
    public RegistrationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    Registration registration = new Registration();
    
    @Test
    public void testCheckUserNameValid() {
        assertTrue(registration.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(registration.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(registration.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(registration.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(registration.checkCellPhoneNumber("+27831234567"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        assertFalse(registration.checkCellPhoneNumber("08966553"));
    }
}
