package org.example.handlers;

import org.example.model.Player;
import org.example.model.json.StreamModeratorsData;
import org.example.model.json.StreamModeratorsDataMain;
import org.example.players.SpotifyPlayer;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.model.spotify.SpotifySearchPage;
import org.example.selenium.model.youtube.SongListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class SkipHandler {

    private Float viewerCount;
    private List<String> votesSubmitted = new ArrayList<>();
    private List<StreamModeratorsData> streamModeratorsData;
    private Player player;

    public SkipHandler(Float viewerCount, List<StreamModeratorsData> streamModeratorsData, Player player) {
        this.viewerCount = viewerCount;
        this.streamModeratorsData = streamModeratorsData;
        this.player = player;
    }

    public Boolean addVote(String user) {
        streamModeratorsData.forEach(moderator -> {
            if (moderator.getUsername().equals(user)){
                skipSongExecution();
            }
        });
        if (!votesSubmitted.contains(user)) {
            votesSubmitted.add(user);
            return true;
        } else {
            return false;
        }
    }

    public Boolean shouldSkip() {
        return (viewerCount / (float) votesSubmitted.size() > 0.2) && (float) votesSubmitted.size() >= 2;
    }

    public void skipSong() {
        if (shouldSkip()) {
            skipSongExecution();
            votesSubmitted.clear();
        }
    }

    private void skipSongExecution() {
        if (player instanceof SpotifyPlayer) {
            SpotifySearchPage spotifySearchPage = new SpotifySearchPage();
            spotifySearchPage.skipCurrentSong();
        } else {
            SongListPage songListPage = new SongListPage();
            songListPage.skipCurrentSong();
        }
    }
}
