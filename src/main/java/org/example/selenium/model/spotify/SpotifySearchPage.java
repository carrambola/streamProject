package org.example.selenium.model.spotify;

import org.example.enums.SongValidationEnum;
import org.example.selenium.config.SeleniumConfig;
import org.example.textApproval.TextApproval;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SpotifySearchPage {

    private WebElement ADD_TO_QUEUE;
    private WebElement BEST_RESULT;
    private WebElement AUTHOR_LABEL;
    private WebElement TITLE_LABEL;
    private WebElement SKIP_BUTTON;


    public SpotifySearchPage() {
        BEST_RESULT = SeleniumConfig.driver.findElements(By.xpath("//div[@data-testid = 'tracklist-row']")).get(0);
        TITLE_LABEL = BEST_RESULT.findElement(By.xpath("./div[2]/div/div"));
        SKIP_BUTTON = SeleniumConfig.driver.findElement(By.xpath("//button[@data-testid = 'control-button-skip-forward']"));
        try{
            AUTHOR_LABEL = BEST_RESULT.findElement(By.xpath("./div[2]/div/span[2]/a"));
        }catch(NoSuchElementException e){
            AUTHOR_LABEL = BEST_RESULT.findElement(By.xpath("./div[2]/div/span/a[1]"));
        }
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

    public SongValidationEnum queueSong(){
        Actions rightclick = new Actions(SeleniumConfig.driver);
        rightclick.moveToElement(BEST_RESULT)
                .contextClick()
                .build()
                .perform();
        ADD_TO_QUEUE = SeleniumConfig.driver.findElement(By.xpath("//span[text() = 'Dodaj do kolejki']"));
        try {
            ADD_TO_QUEUE.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return SongValidationEnum.CORRECT;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ee) {
                ee.printStackTrace();
            }
            return SongValidationEnum.BANNED;
        }
    }

    public String getTitle(){
        return TITLE_LABEL.getText().toLowerCase();
    }

    public String getAuthor() {
        return AUTHOR_LABEL.getText().toLowerCase();
    }

    public void skipCurrentSong(){
        SKIP_BUTTON.click();
    }
}
