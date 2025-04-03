package org.example.enitites;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public static Rating findByValue(String value) {
        for (Rating rating : values()) {
            if (rating.getValue().equals(value)) {
                return rating;
            }
        }

        return null;
    }
}
