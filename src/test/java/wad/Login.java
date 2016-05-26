/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ville-matti
 */
public class Login {
    
    public static boolean onLoginPage(WebDriver driver)
    {
        return driver.getPageSource().contains("forgot your password?");
    }
    
    public static void login(WebDriver driver, String username, String password) {
        driver.get("http://localhost:8080/login");
        WebElement element = driver.findElement(By.name("username")); //Type in the user name
        element.sendKeys(username);

        element = driver.findElement(By.name("password")); //Type in the password
        element.sendKeys(password);

        element = driver.findElement(By.name("submit")); //Click submit
        element.click();
    }
    
    public static void toResistration(WebDriver driver)
    {
        driver.get("http://localhost:8080/login");
        WebElement element = driver.findElement(By.name("register")); //Click the register button
        element.click();
    }
        
}
