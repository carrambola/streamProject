package org.example;

import org.example.players.DesktopPlayer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class DesktopPlayerTest {

    DesktopPlayer player= new DesktopPlayer();

    @BeforeAll
    void beforeAll(){

    }

    @Test
    public void playSong(){
        player.startFirstSong("whoopty.mp3");
    }
}
