package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meal";

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }


    //  @GetMapping("/between?startDate={datetime1}&endDate={datetime2}")
//    public List<MealTo> getBetween(@PathVariable LocalDateTime datetime1, @PathVariable LocalDateTime datetime2) {
//        return super.getBetween(datetime1.toLocalDate(), datetime1.toLocalTime(), datetime2.toLocalDate(), datetime2.toLocalTime());
//    }

    @GetMapping("/between?startDate={date1}&startTime={time1}&endDate={date2}&endTime={time2}")
    public List<MealTo> getBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date1, @PathVariable @DateTimeFormat(pattern = "hh:mm") LocalTime time1,
                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date2, @PathVariable @DateTimeFormat(pattern = "hh:mm") LocalTime time2) {
        return super.getBetween(date1, time1, date2, time2);
        //@DateTimeFormat(pattern="yyyy-MM-dd")  @DateTimeFormat(pattern="hh:mm")
        //@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)              @DateTimeFormat(iso= DateTimeFormat.ISO.TIME)
    }
}