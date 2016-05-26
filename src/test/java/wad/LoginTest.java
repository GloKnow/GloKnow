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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Person;
import wad.repository.PersonRepository;
import wad.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = Application.class, 
    loader = AnnotationConfigWebContextLoader.class)
@TransactionConfiguration(defaultRollback=true)
@WebAppConfiguration
public class LoginTest {
    
    private WebDriver driver;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonRepository personRepository;
    
    public LoginTest() {
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
    public void loginSuccessful()
    {
        driver.get("http://localhost:8080/login");//load the login page
        Login.login(driver, "jackr", "jackr"); //log in
        assertTrue(Activities.onActivitiesPage(driver));
        Activities.goToProfile(driver); //Go to the profile page and check that the current user is Jack
        assertTrue(driver.getPageSource().contains("Jack"));
    }
    
    
    @Test
    @Transactional
    public void registerButtonWorks()
    {
        Login.toResistration(driver);
        assertTrue(SignUp.onRegistrationPage(driver));
    }

}
