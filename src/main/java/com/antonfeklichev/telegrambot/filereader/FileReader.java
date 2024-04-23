package com.antonfeklichev.telegrambot.filereader;

import com.antonfeklichev.telegrambot.entity.English;
import com.antonfeklichev.telegrambot.entity.Word;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

@Component
public class FileReader implements CommandLineRunner {
    @Value(value = "${words.file}")
    String filename;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filename))) {
            bufferedReader.lines().forEach(s -> {
                String[] strings = s.split(" — ");
                String eng = strings[0];
                String rus = strings[1];
                String[] rusArray = rus.split(", ");
                Word engWord = new Word(eng);
                List<Word> rusList = Arrays.stream(rusArray).map(Word::new).toList();
                English english = new English(engWord, rusList);
                entityManager.persist(english);
            });
        }

    }


}
