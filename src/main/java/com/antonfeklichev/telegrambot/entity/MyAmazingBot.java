package com.antonfeklichev.telegrambot.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.abilitybots.api.bot.AbilityBot;


public class MyAmazingBot extends AbilityBot {


    @Autowired
    public MyAmazingBot(@Value("${bot.token}") String botToken,
                        @Value("${bot.username}") String botUsername) {
        super(botToken, botUsername);
    }


}
