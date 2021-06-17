package org.example.players;

import org.example.enums.SongValidationEnum;
import org.example.model.Player;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.model.spotify.SpotifyLogin;
import org.example.selenium.model.spotify.SpotifyMainPage;
import org.example.selenium.model.spotify.SpotifySearchPage;


public class SpotifyPlayer implements Player {

    private final String SPOTIFY_URL = "https://www.spotify.com/pl/";

    public void startBrowser() {
        SeleniumConfig.driver.get(SPOTIFY_URL);
        SpotifyLogin spotifyLogin = new SpotifyLogin();
        spotifyLogin.loginToSpotify();
    }

    public SongValidationEnum findFirstSong(String title) {
        SpotifyMainPage spotifyMainPage = new SpotifyMainPage();
        return spotifyMainPage.searchForSong(title).validateSong();
    }

    public void addToQueueNextSong(String title){
        SpotifySearchPage spotifySearchPage = new SpotifySearchPage();
        spotifySearchPage.queueSong();
    }

    public void startFirstSong(String title){
        addToQueueNextSong(title);
    }

    public SongValidationEnum findNextSong(String title){
        return findFirstSong(title);
    }

    public String getUrl() {
        return SPOTIFY_URL;
    }

}
