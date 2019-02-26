package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.MEALS;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(100002, 100000);
        assertThat(meal).isEqualTo(MEALS.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void getNotOwnedByThisUser() throws Exception {
        service.get(100002, 100001);
    }

    @Test
    public void delete() {
        service.delete(100003, 100000);
        List<Meal> sortedMeals = service.getAll(100000);
        sortedMeals.sort(Comparator.comparing(Meal::getId));
        assertThat(sortedMeals).isEqualTo(Arrays.asList(MEALS.get(0), MEALS.get(2)));
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1, 1);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotOwnedByThisUser() throws Exception {
        service.delete(100002, 100001);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> sortedMeals = service.getBetweenDates(LocalDate.of(2019, Month.FEBRUARY, 25), LocalDate.of(2019, Month.FEBRUARY, 27), 100000);
        sortedMeals.sort(Comparator.comparing(Meal::getId));
        assertThat(sortedMeals).isEqualTo(Arrays.asList(MEALS.get(0), MEALS.get(1), MEALS.get(2)));

    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> sortedMeals = service.getBetweenDateTimes(LocalDateTime.of(2019, Month.FEBRUARY, 26, 8, 0), LocalDateTime.of(2019, Month.FEBRUARY, 26, 10, 0), 100000);
        sortedMeals.sort(Comparator.comparing(Meal::getId));
        assertThat(sortedMeals).isEqualTo(Arrays.asList(MEALS.get(0)));

    }

    @Test
    public void getAll() {
        List<Meal> sortedMeals = service.getAll(100000);
        sortedMeals.sort(Comparator.comparing(Meal::getId));
        assertThat(sortedMeals).isEqualTo(Arrays.asList(MEALS.get(0), MEALS.get(1), MEALS.get(2)));
    }

    @Test
    public void update() {
        Meal updated = new Meal(100002, LocalDateTime.now(), "breakfast", 2000);
        service.update(updated, 100000);
        assertThat(service.get(100002, 100000)).isEqualTo(updated);

    }

    @Test(expected = NotFoundException.class)
    public void updateNotOwnedByThisUser() throws Exception {
        Meal updated = new Meal(100002, LocalDateTime.now(), "breakfast", 2000);
        service.update(updated, 100001);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "breakfastNew", 2000);
        Meal created = service.create(newMeal, 100000);
        newMeal.setId(created.getId());
        List<Meal> sortedMeals = service.getAll(100000);
        sortedMeals.sort(Comparator.comparing(Meal::getId));
        assertThat(sortedMeals).isEqualTo(Arrays.asList(MEALS.get(0), MEALS.get(1), MEALS.get(2), newMeal));

    }
}