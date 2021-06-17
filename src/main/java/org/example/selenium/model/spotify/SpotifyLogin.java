package org.example.selenium.model.spotify;

import org.example.handlers.CredentialsHandler;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.config.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SpotifyLogin {
    private WebElement COOKIES_ACCEPT_SPOTIFY;
    private WebElement SPOTIFY_LOGIN_BUTTON;
    private WebElement CHOICE_FB;
    private WebElement COOKIES_ACCEPT_FB;
    private WebElement EMAIL_SPOT;
    private WebElement PASSWORD_SPOT;
    private WebElement FB_LOGIN_BUTTON;
    CredentialsHandler credentialsHandler = new CredentialsHandler();

    public SpotifyLogin() {
        COOKIES_ACCEPT_SPOTIFY = SeleniumConfig.driver.findElement(By.xpath("//button[@id = 'onetrust-accept-btn-handler']"));
        SPOTIFY_LOGIN_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//a[text() = 'Zaloguj się']"));
    }

    public SpotifyMainPage loginToSpotify() {
        SeleniumUtils.nonClickableElementExceptionHandler(COOKIES_ACCEPT_SPOTIFY);
        SPOTIFY_LOGIN_BUTTON.click();
        CHOICE_FB = SeleniumConfig.driver.findElement(By.xpath("//a[contains(@class, 'btn-facebook')]"));
        CHOICE_FB.click();
        COOKIES_ACCEPT_FB = SeleniumConfig.driver.findElement(By.xpath("//button[@data-testid = 'cookie-policy-dialog-accept-button']"));
        EMAIL_SPOT = SeleniumConfig.driver.findElement(By.xpath("//input[@id = 'email']"));
        PASSWORD_SPOT = SeleniumConfig.driver.findElement(By.xpath("//input[@id = 'pass']"));
        FB_LOGIN_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//button[@id = 'loginbutton']"));
        COOKIES_ACCEPT_FB.click();
        EMAIL_SPOT.sendKeys(credentialsHandler.getLogin());
        PASSWORD_SPOT.sendKeys(credentialsHandler.getPassword());
        FB_LOGIN_BUTTON.click();
        return new SpotifyMainPage();
    }
}
