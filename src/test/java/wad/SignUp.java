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
public class SignUp {
    
    public static boolean onRegistrationPage(WebDriver driver)
    {
        return driver.getPageSource().contains("Password again");
    }
    
    public static void register(WebDriver driver, String firstname, String surname, 
                                String username, String phone, String email, 
                                String birthday, String password)
    {
        driver.get("http://localhost:8080/signup");
        WebElement element;
        
        element = driver.findElement(By.name("firstname"));
        element.sendKeys(firstname);
        element = driver.findElement(By.name("surname"));
        element.sendKeys(surname);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("phone"));
        element.sendKeys(phone);
        element = driver.findElement(By.name("email"));
        element.sendKeys(email);
        element = driver.findElement(By.name("birthday"));
        element.sendKeys(birthday);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("password again"));
        element.sendKeys(password);
        
        element = driver.findElement(By.name("submit"));
        element.click();
    }
}
