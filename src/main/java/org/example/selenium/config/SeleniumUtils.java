package org.example.selenium.config;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class SeleniumUtils {
    public static WebElement staleExceptionHandler(String selector){
        while (true) {
            try{
                SeleniumConfig.driver.findElement(By.xpath(selector)).getText();
                return SeleniumConfig.driver.findElement(By.xpath(selector));
            }catch(StaleElementReferenceException e){
                return null;
            }
        }
    }
    public static void nonClickableElementExceptionHandler(WebElement element){
        while (true) {
            try{
                element.click();
                break;
            }catch(ElementClickInterceptedException e){
            }
        }
    }
}
