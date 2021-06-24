package org.example.Bot;

import org.example.Bot.client.MessageHandler;
import org.example.enums.SongValidationEnum;
import org.example.handlers.SkipHandler;
import org.example.model.Player;
import org.example.players.SpotifyPlayer;
import org.example.players.YoutubePlayer;
import org.example.selenium.config.SeleniumConfig;
import org.example.twitchApi.TwitchApiRepo;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import java.io.IOException;

public class BotRunner extends PircBot {

    private MessageHandler messageHandler = new MessageHandler();
    public static Player player = new SpotifyPlayer();
    TwitchApiRepo twitchApiRepo = new TwitchApiRepo();

    public void onMessage(String channel, String sender, String login, String hostname, String message){
        if (message.startsWith("!sr")) {
            songRequest(message,sender,channel);
        } else if (message.startsWith("!sv")) {
            SkipHandler skipHandler = new SkipHandler(twitchApiRepo.getLiveUserCount().floatValue(),twitchApiRepo.getModerators(), player);
            skipHandler.addVote(sender);
            skipHandler.skipSong();
        }
    }

    public static void twitchApiStartUp(){
        BotRunner bot = new BotRunner();
        bot.setName("carrambola");
        bot.setLogin("carrambola");
        bot.setVerbose(true);
        try {
            bot.connect("irc.twitch.tv", 6667, "oauth:rqzwcdxqzj8uhsm5lh2wblcsst521w");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
        bot.joinChannel("#carrambola");
    }

    private void songRequest(String message, String sender, String channel){
        String song = messageHandler.formatCommand(message);
        if (SeleniumConfig.driver.getCurrentUrl().equals(player.getUrl())) {
            SongValidationEnum songValidation =  player.findFirstSong(song);
            if (songValidation == SongValidationEnum.CORRECT){
                player.startFirstSong(song);
                sendMessage(channel, messageHandler.confirmSong(sender,song));

            }else if(songValidation == SongValidationEnum.BANNED){
                sendMessage(channel, messageHandler.rejectSong(sender));
            }else
            {
                sendMessage(channel, messageHandler.invalidSong(sender,song));
            }
        } else {
            SongValidationEnum songValidationNext = player.findNextSong(song);
            if (songValidationNext == SongValidationEnum.CORRECT){
                player.addToQueueNextSong(song);
                sendMessage(channel, messageHandler.confirmSong(sender,song));
            }else if(songValidationNext == SongValidationEnum.BANNED){
                sendMessage(channel, messageHandler.rejectSong(sender));
            }else
            {
                sendMessage(channel, messageHandler.invalidSong(sender,song));
            }
        }
    }
}
