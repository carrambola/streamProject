package org.example;

import org.example.Bot.BotRunner;
import org.example.selenium.config.SeleniumConfig;
import org.jibble.pircbot.PircBot;


public class Main extends PircBot {


    public static void main(String[] args) {
        SeleniumConfig.setDriver();
        BotRunner.player.startBrowser();
        BotRunner.twitchApiStartUp();
    }
}


