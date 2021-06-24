package org.example.selenium.model.youtube;

import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.config.SeleniumUtils;
import org.example.textApproval.TextApproval;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.example.enums.SongValidationEnum;
import org.openqa.selenium.interactions.Actions;


public class SongListPage {


    private WebElement PLAY_BUTTON;
    private String ADD_TO_QUEUE_SELECTOR = "//yt-formatted-string[text()='Dodaj do kolejki']";
    private WebElement AUTHOR_LABEL;
    private WebElement TITLE_LABEL;
    private WebElement SEARCH_BOX;
    private WebElement SONGS_BUTTON;
    private WebElement SKIP_BUTTON_YT;

    public SongListPage() {
        PLAY_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//ytmusic-play-button-renderer[contains(@size, 'MUSIC_PLAY_BUTTON_SIZE_SMALL')]"));
        TITLE_LABEL = SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[1]/yt-formatted-string/a"));
        SEARCH_BOX = SeleniumConfig.driver.findElement(By.xpath("//input[contains(@class,'style-scope ytmusic-search-box')]"));
        SONGS_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//yt-formatted-string[text() = 'Utwory']"));
        AUTHOR_LABEL = SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[3]/yt-formatted-string/a[1]"));
        SKIP_BUTTON_YT = SeleniumConfig.driver.findElement(By.xpath("//tp-yt-paper-icon-button[@title = 'Następny utwór']"));
    }

    public SongListPage playFirstSong() {
        PLAY_BUTTON.click();
        return this;
    }

    public SongValidationEnum validateSong() {
        if (checkIfSongApproved()) {
            return SongValidationEnum.CORRECT;
        }
        return SongValidationEnum.BANNED;
    }

    private Boolean checkIfSongApproved() {
        TextApproval textApproval = new TextApproval();
        try {
            Thread.sleep(2000);
            return textApproval.verifyLyrics(getAuthor(), getTitle()) && textApproval.scanText(getAuthor()) && textApproval.scanText(getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public SongListPage addToQueueNextSong() {
        Actions rightclick = new Actions(SeleniumConfig.driver);
        while (true) {
            try {
                rightclick.moveToElement(PLAY_BUTTON).contextClick().build().perform();
                SeleniumConfig.driver.findElement(By.xpath(ADD_TO_QUEUE_SELECTOR)).click();
                break;
            } catch (Exception e) {
                PLAY_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//ytmusic-play-button-renderer[contains(@size, 'MUSIC_PLAY_BUTTON_SIZE_SMALL')]"));
            }
        }
        return this;
    }

    public SongListPage searchForNextSong(String song) {
        SEARCH_BOX.clear();
        SEARCH_BOX.sendKeys(song);
        SEARCH_BOX.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2000);
            SONGS_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//yt-formatted-string[text() = 'Utwory']"));
            SONGS_BUTTON.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    public String getAuthor() {
        AUTHOR_LABEL = SeleniumUtils.staleExceptionHandler("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[3]/yt-formatted-string/a[1]");
        return AUTHOR_LABEL.getText().toLowerCase();
    }

    public String getTitle() {
        TITLE_LABEL = SeleniumUtils.staleExceptionHandler("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[1]/yt-formatted-string/a");
        return TITLE_LABEL.getText().toLowerCase();
    }

    public SongListPage skipCurrentSong(){
        SKIP_BUTTON_YT.click();
        return this;
    }
}
