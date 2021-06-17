package org.example.handlers;

import org.example.model.Player;
import org.example.players.SpotifyPlayer;
import org.example.selenium.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class SkipHandler{

    Float viewerCount;
    List<String> votesSubmitted = new ArrayList<>();

    public SkipHandler(Float viewerCount) {
        this.viewerCount = viewerCount;
    }

    public Boolean addVote(String user) {
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

    public void skipSong(Player player) {
        if (shouldSkip()) {
            if (player instanceof SpotifyPlayer) {
                SeleniumConfig.driver.findElement(By.xpath("//button[@data-testid = 'control-button-skip-forward']")).click();
            } else {
                SeleniumConfig.driver.findElement(By.xpath("//tp-yt-paper-icon-button[@title = 'Następny utwór']")).click();
            }
            votesSubmitted.clear();
        }
    }
}
