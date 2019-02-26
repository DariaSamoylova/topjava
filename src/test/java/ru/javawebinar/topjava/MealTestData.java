package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;


public class MealTestData {

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100002, LocalDateTime.of(2019, Month.FEBRUARY, 26, 9, 0), "Breakfast", 500),
            new Meal(100003, LocalDateTime.of(2019, Month.FEBRUARY, 26, 14, 0), "Dinner", 500),
            new Meal(100004, LocalDateTime.of(2019, Month.FEBRUARY, 26, 19, 0), "Supper", 500),
            new Meal(100005, LocalDateTime.of(2019, Month.FEBRUARY, 26, 9, 0), "Breakfast", 500),
            new Meal(100006, LocalDateTime.of(2019, Month.FEBRUARY, 26, 14, 0), "Dinner", 500),
            new Meal(100007, LocalDateTime.of(2019, Month.FEBRUARY, 26, 19, 0), "Supper", 1800)
    );
    //public static void assertMatch(Meal actual, Meal expected) {
    //assertThat(actual).isEqualToIgnoringGivenFields(expected, "user_id");
    // }
   /*
    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
    */
}
