package org.example.handlers;

import org.example.model.Player;
import org.example.players.SpotifyPlayer;
import org.example.selenium.config.SeleniumConfig;
import org.example.selenium.model.spotify.SpotifySearchPage;
import org.example.selenium.model.youtube.SongListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class SkipHandler {

    Float viewerCount;
    List<String> votesSubmitted = new ArrayList<>();

    public SkipHandler(Float viewerCount) {
        this.viewerCount = viewerCount;
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
}
