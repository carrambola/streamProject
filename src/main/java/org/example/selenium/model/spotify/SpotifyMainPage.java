package org.example.selenium.model.spotify;

import org.example.selenium.config.SeleniumConfig;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpotifyMainPage {
    private final String DEFAULT_SPOTIFY_URL;

    public SpotifyMainPage() {
       DEFAULT_SPOTIFY_URL = "https://open.spotify.com/";
    }

    public SpotifySearchPage searchForSong(String title){
        while (true) {
            SeleniumConfig.driver.get(DEFAULT_SPOTIFY_URL);
            if (SeleniumConfig.driver.getCurrentUrl().equals(DEFAULT_SPOTIFY_URL)) {
                title = title.replaceAll(" ", "%20");
                SeleniumConfig.driver.get(SeleniumConfig.driver.getCurrentUrl() + "search/" + title + "/tracks");
                break;
            }
        }
        return new SpotifySearchPage();
    }
}
