package org.example.selenium.model.youtube;


import org.example.selenium.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    private WebElement SEARCH_BAR;
    private WebElement SEARCH_BAR_INPUT;


    public MainPage() {
        SEARCH_BAR = SeleniumConfig.driver.findElement(By.xpath("//tp-yt-paper-icon-button[contains(@title,'Rozpocznij wyszukiwanie')]"));
        SEARCH_BAR_INPUT = SeleniumConfig.driver.findElement(By.xpath("//input[contains(@placeholder,'Szukaj')]"));
    }

    public MainPage startSearching() {
        SEARCH_BAR.click();
        return this;
    }


    public SongListPage searchForSong(String song) {
        SEARCH_BAR_INPUT.sendKeys(song);
        SEARCH_BAR_INPUT.sendKeys(Keys.ENTER);
        return new SongListPage();
    }


}
