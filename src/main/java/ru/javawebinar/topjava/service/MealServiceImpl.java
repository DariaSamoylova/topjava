package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal, Integer userId) {
        Meal savedMeal = repository.save(meal, userId);
        if (savedMeal == null)
            throw new NotFoundException(meal + " is not owned by user with userid = " + userId);
        return savedMeal;

    }

    @Override
    public void delete(int id, Integer userId) throws NotFoundException {
        repository.delete(id, userId);

    }

    @Override
    public Meal get(int id, Integer userId) throws NotFoundException {
        Meal resultMeal = repository.get(id, userId);
        if (resultMeal == null)
            throw new NotFoundException("Meal with id = " + id + " is not owned by user with userid = " + userId);
        return resultMeal;
    }

    @Override
    public void update(Meal meal, Integer userId) {
        Meal savedMeal = repository.save(meal, userId);
        if (savedMeal == null)
            throw new NotFoundException(meal + " is not owned by user with userid = " + userId);


    }

    @Override
    public List<Meal> getAll(Integer userId) {
        List<Meal> mealList = (List<Meal>) repository.getAll(userId);
        if (mealList.isEmpty())
            throw new NotFoundException("no meals owned by user with userid = " + userId);
        return mealList;
    }

    @Override
    public List<Meal> getAllToFiltered(LocalDate d1, LocalDate d2, LocalTime t1, LocalTime t2, Integer userId) {
        List<Meal> mealList = repository.getAllToFiltered(d1, d2, t1, t2, userId);
        if (mealList.isEmpty())
            throw new NotFoundException("no meals with this parameters owned by user with userid = " + userId);
        return mealList;
    }
}