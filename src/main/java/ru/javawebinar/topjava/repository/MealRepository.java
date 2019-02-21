package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, Integer userId);

    void delete(int id, Integer userId);

    Meal get(int id, Integer userId);

    Collection<Meal> getAll(Integer userId);

    List<Meal> getAllToFiltered(LocalDate d1, LocalDate d2, LocalTime t1, LocalTime t2, Integer userId);
}
