/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = Application.class, 
    loader = AnnotationConfigWebContextLoader.class)
@TransactionConfiguration(defaultRollback=true)
@WebAppConfiguration
public class SignUpTest {
    
    private WebDriver driver;
    
    public SignUpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void RegistrationWorks()
    {
        String firstname = "Unit";
        String surname = "Tester";
        String username = "uTester";
        String phone = "1234567890";
        String email = "unit@testing.test";
        String birthday = "01/01/1991";
        String password = "TestAllTheThings";
        SignUp.register(driver, firstname, surname, username, phone, email, birthday, password);
        Login.login(driver, username, password);
        Activities.goToProfile(driver);
        assertTrue(driver.getPageSource().contains(firstname));
    }
}
