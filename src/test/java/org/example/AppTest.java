//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.util.concurrent.TimeUnit;

import org.example.model.Player;
import org.example.players.DesktopPlayer;
import org.example.players.SpotifyPlayer;
import org.example.players.YoutubePlayer;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.model.spotify.SpotifyMainPage;
import org.example.selenium.model.spotify.SpotifySearchPage;
import org.example.selenium.model.youtube.MainPage;
import org.example.selenium.model.youtube.SongListPage;
import org.example.textApproval.TextApproval;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
    static Player player;
    static TextApproval textTester;


    @BeforeAll
    static void beforeAll() {
        SeleniumConfig.setDriver();
        player = new YoutubePlayer();
        textTester = new TextApproval();
    }

    @BeforeEach
    void beforeEach() {
        SeleniumConfig.driver.close();
        SeleniumConfig.driver = new ChromeDriver();
        SeleniumConfig.driver.manage().window().maximize();
        SeleniumConfig.driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
    }

    @Test
    public void checkIfPlayingFirstSongProcessWorks() {
        if(player instanceof DesktopPlayer){
            player.startFirstSong("whoopty.mp3");
        }else{
            player.startBrowser();
            player.findFirstSong("whoopty cj");
            player.startFirstSong("whoopty cj");
        }


    }

    @Test
    public void checkSongData() {
        player.startBrowser();
        player.findFirstSong("whoopty cj");
        if (player instanceof YoutubePlayer) {
            Assert.assertEquals(SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[3]/yt-formatted-string/a[1]")).getText().toLowerCase(), "cj");
            Assert.assertEquals(SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[1]/yt-formatted-string/a")).getText().toLowerCase(), "whoopty");
        } else {
            SpotifyMainPage spotifyMainPage = new SpotifyMainPage();
            Assert.assertEquals(spotifyMainPage.searchForSong("whoopty").getTitle().toLowerCase(), "whoopty");
            Assert.assertEquals(spotifyMainPage.searchForSong("whoopty").getAuthor().toLowerCase(), "cj");
        }
    }

    @Test
    public void getUrlActionTest() {
        player.startBrowser();
        player.findFirstSong("whoopty cj");
        if (player instanceof YoutubePlayer) {
            String validTitle = SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[1]/yt-formatted-string/a")).getText().toLowerCase();
            String validAuthor = SeleniumConfig.driver.findElement(By.xpath("//div[@id='contents']/ytmusic-shelf-renderer[1]/div[2]/ytmusic-responsive-list-item-renderer/div[2]/div[3]/yt-formatted-string/a[1]")).getText().toLowerCase();
            String testedUrl = "https://www.tekstowo.pl/piosenka," + validAuthor + "," + validTitle + ".html";
            Assert.assertEquals(testedUrl, "https://www.tekstowo.pl/piosenka,cj,whoopty.html");
        } else {
            SpotifySearchPage spotifySearchPage = new SpotifySearchPage();
            String validTitle = spotifySearchPage.getTitle().toLowerCase();
            String validAuthor = spotifySearchPage.getAuthor().toLowerCase();
            String testedUrl = "https://www.tekstowo.pl/piosenka," + validAuthor + "," + validTitle + ".html";
            Assert.assertEquals(testedUrl, "https://www.tekstowo.pl/piosenka,cj,whoopty.html");
        }

    }

    @Test
    public void queueRequirementsTestTrue() {
        player.startBrowser();
        if (player instanceof YoutubePlayer) {
            MainPage mainpage = new MainPage();
            SongListPage songListPage = mainpage.startSearching().searchForSong("justin bieber baby");
            Assert.assertTrue(textTester.verifyLyrics(songListPage.getAuthor(), songListPage.getTitle()));
        } else {
            SpotifyMainPage spotifyMainPage = new SpotifyMainPage();
            SpotifySearchPage spotifySearchPage = spotifyMainPage.searchForSong("justin bieber baby");
            Assert.assertTrue(textTester.verifyLyrics(spotifySearchPage.getAuthor(), spotifySearchPage.getTitle()));
        }

    }

    @Test
    public void queueRequirementsTestFalse() {
        player.startBrowser();
        if (player instanceof YoutubePlayer) {
            MainPage mainpage = new MainPage();
            SongListPage songListPage = mainpage.startSearching().searchForSong("whoopty cj");
            Assert.assertFalse(textTester.verifyLyrics(songListPage.getAuthor(), songListPage.getTitle()));
        } else {
            SpotifyMainPage spotifyMainPage = new SpotifyMainPage();
            SpotifySearchPage spotifySearchPage = spotifyMainPage.searchForSong("whoopty");
            Assert.assertFalse(textTester.verifyLyrics(spotifySearchPage.getAuthor(), spotifySearchPage.getTitle()));
        }
    }

    @Test
    public void checkIfSkipSongWorksCorrectly(){
        player.startBrowser();
        if(player instanceof YoutubePlayer){
            MainPage mainpage = new MainPage();
            SongListPage songListPage = mainpage.startSearching().searchForSong("justin bieber baby")
                    .playFirstSong()
                    .skipCurrentSong()
                    .searchForNextSong("justing bieber baby")
                    .addToQueueNextSong();

        }
    }

    @AfterAll
    static void afterAll() {
        SeleniumConfig.driver.close();
    }
}
