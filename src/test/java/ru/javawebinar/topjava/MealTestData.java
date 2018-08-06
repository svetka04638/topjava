package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(1999,01,8,9,05,06), "Syrnichki", 420),
            new Meal(LocalDateTime.of(1999,01,8,17,05,06),"Katletki",800)

    );

    public static Meal FIRST_MEAL= MEALS.get(0);

    public static void assertMatch(Meal actual,Meal expected){
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected){
        assertThat(actual).isEqualTo(expected);
    }


}
