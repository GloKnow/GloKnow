/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ville-matti
 */
public class Activities {
    
    public static boolean onActivitiesPage(WebDriver driver)
    {
        return driver.getPageSource().contains("Propose new activity");
    }
    
    public static void goToProfile(WebDriver driver)
    {
        driver.get("http://localhost:8080/activities");
        WebElement element = driver.findElement(By.name("profile"));
        element.click(); 
    }
    
    public static void goToSettings(WebDriver driver)
    {
        driver.get("http://localhost:8080/activities");
        WebElement element = driver.findElement(By.name("settings"));
        element.click(); 
    }
    
    public static void goToHere(WebDriver driver) //This is stupid.
    {
        driver.get("http://localhost:8080/activities");
        WebElement element = driver.findElement(By.name("mainPage"));
        element.click(); 
        //alternate method:
        //pass;
    }
    
    public static void goToActivityCreation(WebDriver driver)
    {
        driver.get("http://localhost:8080/activities");
        WebElement element = driver.findElement(By.name("create"));
        element.click(); 
    }
    
    
    public static void joinActivity(WebDriver driver, String activityName)
    {
        WebElement activity = findActivity(driver, activityName);
        WebElement joinButton = activity.findElement(By.xpath(".//input[@value='Join!']"));
        joinButton.click();
        
    }
    
    public static void removeActivity(WebDriver driver, String activityName)
    {
        WebElement activity = findActivity(driver, activityName);
        WebElement removeButton = activity.findElement(By.xpath(".//input[@value='Remove!']"));
        removeButton.click();
    }
    
    public static WebElement findActivity(WebDriver driver, String activityName)
    {
        driver.get("http://localhost:8080/activities");
        WebElement activityLink = driver.findElement(By.linkText(activityName));
        WebElement activity = activityLink.findElement(By.xpath(".."))
                .findElement(By.xpath("..")).findElement(By.xpath(".."))
                .findElement(By.xpath("..")).findElement(By.xpath(".."))
                .findElement(By.xpath("..")); //Parent of a parent of a prarent of a ...
        return activity;
    }
    
    public static boolean existsActivity(WebDriver driver, String activityName)
    {
        driver.get("http://localhost:8080/activities");
        try 
        {
            WebElement activityLink = driver.findElement(By.linkText(activityName));
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    
    public static int getActivityAttendeeCount(WebDriver driver, String activityName)
    {
        WebElement activity = findActivity(driver,activityName);
        WebElement attendeeCount = activity.findElement(By.xpath(".//td[@class='attendeeCount']"));
        return Integer.parseInt(attendeeCount.getText());
    }
}
