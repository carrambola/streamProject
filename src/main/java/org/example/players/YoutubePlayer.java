package org.example.players;

import org.example.enums.SongValidationEnum;
import org.example.model.Player;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.model.youtube.MainPage;
import org.example.selenium.model.youtube.SongListPage;
import org.openqa.selenium.chrome.ChromeDriver;

public class YoutubePlayer implements Player {

    private final String YOUTUBE_URL = "https://music.youtube.com/";

    public void startBrowser() {
        SeleniumConfig.driver.get(YOUTUBE_URL);
    }

    public void startFirstSong(String title) {
        SongListPage songListPage = new SongListPage();
        songListPage.playFirstSong();
    }

    public SongValidationEnum findFirstSong(String title) {
        MainPage mainPage = new MainPage();
        return mainPage.startSearching().searchForSong(title).validateSong();
    }

    public SongValidationEnum findNextSong(String title) {
        SongListPage mainPage = new SongListPage();
        return mainPage.searchForNextSong(title).validateSong();
    }

    public void addToQueueNextSong(String title) {
        SongListPage songListPage = new SongListPage();
        songListPage.addToQueueNextSong();
    }

    public String getUrl() {
        return YOUTUBE_URL;
    }
}
