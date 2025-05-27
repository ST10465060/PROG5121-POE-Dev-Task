/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.registrationloginsystem.Registration;
import com.mycompany.registrationloginsystem.Login;
/**
 *
 * @author eduan
 */
public class LoginTest {
    
    public LoginTest() {
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
    

    @Test
    public void testLoginSuccess() {
        Registration reg = new Registration();
        // Add first and last name parameters
        reg.registerUser("Test", "User", "kyl_1", "Ch&&sec@ke99!", "+27831234567");

        Login login = new Login();
        login.setRegisteredUser(reg);
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginFailure() {
        Registration reg = new Registration();
        // Add first and last name parameters
        reg.registerUser("Test", "User", "kyl_1", "Ch&&sec@ke99!", "+27831234567");
        
        Login login = new Login();
        login.setRegisteredUser(reg);
        assertFalse(login.loginUser("wrong", "credentials"));
    }
}
