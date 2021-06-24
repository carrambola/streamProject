package org.example.players;



import javazoom.jl.player.Player;
import java.io.FileInputStream;



public class DesktopPlayer {



    public void startFirstSong(String title) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(title);
            Player playMP3 = new Player(fis);
            playMP3.play();
        }catch (Exception e){
        }
    }
}
