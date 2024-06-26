package com.antonfeklichev.telegrambot.entity;

import com.antonfeklichev.telegrambot.repository.BotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.List;
import java.util.stream.Collectors;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;


@Component
@Slf4j
public class MyAmazingBot extends AbilityBot {

    private final BotRepository botRepository;

    @Autowired
    public MyAmazingBot(@Value("${bot.token}") String botToken,
                        @Value("${bot.username}") String botUsername,
                        BotRepository botRepository) {
        super(botToken, botUsername);
        this.botRepository = botRepository;
        log.info("Bot created");
    }


    @Override
    public long creatorId() {
        return 1L;
    }


    public Ability delete() {
        return Ability
                .builder()
                .name("delete")
                .info("Deletes the bot's last message")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    long chatId = ctx.chatId();
                    String stringByChatId = String.valueOf(chatId);
                    int messageId = ctx.update().getMessage().getMessageId();
                    silent.execute(new DeleteMessage(stringByChatId, messageId));

                })
                .build();
    }


    public Ability translate() {
        return Ability
                .builder()
                .name("translate")
                .info("says hello world!")
                .input(1)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    String letter = ctx.firstArg();
                    List<English> translateList = botRepository.getEnglishByFirstLetter(letter);
                    String stringTranslations = translateList.stream()
                            .map(English::toString)
                            .collect(Collectors.joining("\n\n"));
                    silent.send(stringTranslations, ctx.chatId());
                })
                .build();
    }

}
