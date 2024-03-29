package com.antonfeklichev.telegrambot.repository;

import com.antonfeklichev.telegrambot.entity.English;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BotRepository extends JpaRepository<English, Long> {

    @Query("SELECT e " +
           "FROM English e " +
           "WHERE e.word.word LIKE :letter||'%'"
    )
    List<English> getEnglishByFirstLetter(String letter);
}
