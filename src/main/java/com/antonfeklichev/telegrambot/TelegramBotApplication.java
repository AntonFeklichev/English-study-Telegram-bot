package com.antonfeklichev.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramBotApplication {
//    @Autowired
//    MyAmazingBot myAmazingBot;

    public static void main(String[] args) throws Exception {


//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        botsApi.registerBot(new MyAmazingBot());

        var ctx = SpringApplication.run(TelegramBotApplication.class, args);

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(ctx.getBean("myAmazingBot", AbilityBot.class));

    }

}
