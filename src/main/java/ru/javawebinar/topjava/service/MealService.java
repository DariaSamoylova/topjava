package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal create(Meal meal, Integer userId);

    void delete(int id, Integer userId) throws NotFoundException;

    Meal get(int id, Integer userId) throws NotFoundException;


    void update(Meal meal, Integer userId);

    List<Meal> getAll(Integer userId);

    List<Meal> getAllToFiltered(LocalDate d1, LocalDate d2, LocalTime t1, LocalTime t2, Integer userId);
}