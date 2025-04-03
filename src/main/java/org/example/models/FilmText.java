package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

@Entity
@Table(name = "film_text")
@Getter
@Setter
@NoArgsConstructor
public class FilmText {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Length.LONG16)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
}
