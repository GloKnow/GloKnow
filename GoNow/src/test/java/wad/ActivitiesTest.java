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
public class ActivitiesTest {
    
    private WebDriver driver;
    
    public ActivitiesTest() {
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

    //Before logging in, trying to do anything should send you to the login page.
    
    @Test
    @Transactional
    public void preLoginProfileSendsToLogin()
    {
        Activities.goToProfile(driver);
        assertTrue(Login.onLoginPage(driver));
    }
    
    @Test
    @Transactional
    public void preLoginSettingsSendsToLogin()
    {
        Activities.goToSettings(driver);
        assertTrue(Login.onLoginPage(driver));
    }
    
    //Clicking the button that takes you to the activities page while on the activities page
    //is not exactlt exciting.
    @Test
    @Transactional
    public void preLoginActivitiesChangesNothing()
    {
        Activities.goToHere(driver);
        assertTrue(Activities.onActivitiesPage(driver));
    }
    
    @Test
    @Transactional
    public void preLoginProposeNewActivitySendsToLogin()
    {
        Activities.goToActivityCreation(driver);
        assertTrue(Login.onLoginPage(driver));
    }
    
    @Test
    @Transactional
    public void preLoginJoinActivitySendsToLogin()
    {
        String activityName = "Marathon";
        Activities.joinActivity(driver, activityName);
        assertTrue(Login.onLoginPage(driver));
        assertEquals(0,Activities.getActivityAttendeeCount(driver, activityName));
    }
    
    @Test
    @Transactional
    public void preLoginRemoveActivitySendsToLogin()
    {
        String activityName = "Football";
        assertTrue(Activities.existsActivity(driver, activityName));
        Activities.removeActivity(driver, activityName);
        assertTrue(Login.onLoginPage(driver));
        assertTrue(Activities.existsActivity(driver, activityName));
    }
    
    //After logging in, the buttons should take you to their respective pages.
    @Test
    @Transactional
    public void postLoginProfileSendsToProfile()
    {
        Login.login(driver, "jackr", "jackr");
        Activities.goToProfile(driver);
        assertTrue(driver.getPageSource().contains("Jack"));
    }
    
    @Test
    @Transactional
    public void postLoginSettignsSendsToSettings()
    {
        Login.login(driver, "jackr", "jackr");
        Activities.goToSettings(driver);
        assertTrue(driver.getPageSource().contains("Private profile"));
    }
    
    @Test
    @Transactional
    public void postLoginActivitiesPageStillChangesNothing()
    {
        Login.login(driver, "jackr", "jackr");
        Activities.goToHere(driver);
        assertTrue(Activities.onActivitiesPage(driver));
    }
    
    @Test
    @Transactional
    public void postLoginJoinActivityIncreasesCounter()
    {
        Login.login(driver, "jackr", "jackr");
        String activityName = "Marathon";
        assertEquals(0, Activities.getActivityAttendeeCount(driver, activityName));
        Activities.joinActivity(driver, activityName);
        assertEquals(1, Activities.getActivityAttendeeCount(driver, activityName));
        Activities.leaveActivity(driver, activityName); //Cleanup
        assertEquals(0, Activities.getActivityAttendeeCount(driver, activityName)); //Check if we actually left the activity
    }
    
    
    @Test
    @Transactional
    public void cannotRemoveNonOwnedActivities()
    {
        Login.login(driver, "jackr", "jackr");
        String activityName = "Free coffee!";
        assertTrue(Activities.existsActivity(driver, activityName));
        Activities.removeActivity(driver, activityName);
        assertTrue(Activities.existsActivity(driver, activityName));
    }
    
    @Test
    @Transactional
    public void postLoginRemoveActivityWorks()
    {
        Login.login(driver, "jackr", "jackr");
        String activityName = "Football";
        assertTrue(Activities.existsActivity(driver, activityName));
        Activities.removeActivity(driver, activityName);
        assertFalse(Activities.existsActivity(driver, activityName));
    }
    
    @Test
    @Transactional
    public void postLoginJoinActivityDoesNotIncreaseCounterTwice()
    {
        Login.login(driver, "jackr", "jackr");
        String activityName = "Marathon";
        assertEquals(0,Activities.getActivityAttendeeCount(driver, activityName));
        Activities.joinActivity(driver, activityName);
        assertEquals(1,Activities.getActivityAttendeeCount(driver, activityName));
        Activities.joinActivity(driver, activityName);
        assertEquals(1,Activities.getActivityAttendeeCount(driver, activityName));
        
        Activities.leaveActivity(driver, activityName); //Cleanup
        assertEquals(0, Activities.getActivityAttendeeCount(driver, activityName)); //Check if we actually left the activity
    }

}
