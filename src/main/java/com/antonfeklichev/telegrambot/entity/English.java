package com.antonfeklichev.telegrambot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "english")
public class English {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @PrimaryKeyJoinColumn
    //@Column(name = "english_words")
    @OneToOne
    @Cascade(CascadeType.PERSIST)
    private Word word;

    @OneToMany
    @Cascade(value = CascadeType.PERSIST)
    private List<Word> russian;

    public English(Word word, List<Word> russian) {
        this.word = word;
        this.russian = russian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        English english = (English) o;
        return Objects.equals(id, english.id) && Objects.equals(word, english.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }
}
