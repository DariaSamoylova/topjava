package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        if (!meal.getUserId().equals(userId))
            return null;
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id, Integer userId) {
        if (repository.get(id) == null || !repository.get(id).getUserId().equals(userId))
            return;

        repository.remove(id);
        //NPE
    }

    @Override
    public Meal get(int id, Integer userId) {
        if (repository.get(id) == null || !repository.get(id).getUserId().equals(userId))
            return null;

        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.values()
                .stream()
                .filter(m -> m.getUserId().equals(userId))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllToFiltered(LocalDate d1, LocalDate d2, LocalTime t1, LocalTime t2, Integer userId) {
        if (d1 == null) d1 = LocalDate.MIN;
        if (d2 == null) d2 = LocalDate.MAX;
        if (t1 == null) t1 = LocalTime.MIN;
        if (t2 == null) t2 = LocalTime.MAX;
        LocalDateTime localDateTime1 = LocalDateTime.of(d1, t1);
        LocalDateTime localDateTime2 = LocalDateTime.of(d2, t2);
        Collection<Meal> meals = getAll(userId);
        return meals.stream()
                .filter(m -> DateTimeUtil.isBetween(m.getDateTime(), localDateTime1, localDateTime2))

                .collect(Collectors.toList());

    }
}

