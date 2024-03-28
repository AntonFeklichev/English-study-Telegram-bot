package com.antonfeklichev.telegrambot;

import com.antonfeklichev.telegrambot.entity.MyAmazingBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) throws Exception {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new MyAmazingBot());

        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
