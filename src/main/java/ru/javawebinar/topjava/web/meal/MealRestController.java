package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.AbstractMealController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {
    // private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    // private final MealService service;

    // @Autowired
    //public MealRestController(MealService service) {
    //     this.service = service;
    //}

    public Meal get(int id) {
        return super.get(id);
    }

    public void delete(int id) {

        super.delete(id);
    }

    public List<MealTo> getAll() {
        // int userId = SecurityUtil.authUserId();
        //log.info("getAll for user {}", userId);
        //return MealsUtil.getWithExcess(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
        return super.getAll();
    }

    public Meal create(Meal meal) {
        return super.create(meal);
    }

    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}