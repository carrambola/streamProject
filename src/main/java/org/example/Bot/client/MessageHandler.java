package org.example.Bot.client;

import org.example.Bot.BotRunner;


public class MessageHandler {

    public String formatCommand(String text){
        return text.replace("!sr", "");
    }

    public String confirmSong(String sender,String song){
        return "Just for you, " + sender + ". Queued requested song: " + song;
    }

    public String rejectSong(String sender){
        return "Just for you, " + sender + ". You nasty little fucker, there are some bad words in a song !!!!";
    }

    public String invalidSong(String sender,String song){
        return sender + " Use more detailed title of: " + song;
    }
}
