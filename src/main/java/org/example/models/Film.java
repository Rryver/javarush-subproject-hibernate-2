package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enitites.Rating;
import org.example.models.converters.EnumValueConverter;
import org.example.models.converters.MySqlSetConverter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.Length.LONG16;

@Entity
@Table(name = "film")
@Getter
@Setter
@NoArgsConstructor
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", length = LONG16)
    private String description;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "release_year")
    private Date releaseYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17')")
    @Convert(converter = EnumValueConverter.class)
    private Rating rating;

    @Convert(converter = MySqlSetConverter.class)
    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private Set<String> specialFeatures;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    @OneToOne(mappedBy = "film", fetch = FetchType.LAZY)
    private FilmText text;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
