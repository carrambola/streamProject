package org.example.model;

import org.example.enums.SongValidationEnum;
import org.openqa.selenium.chrome.ChromeDriver;

public interface Player {
     void startFirstSong(String title);
     void startBrowser();
     String getUrl();
     SongValidationEnum findFirstSong(String title);
     SongValidationEnum findNextSong(String title);
     void addToQueueNextSong(String title);

}
