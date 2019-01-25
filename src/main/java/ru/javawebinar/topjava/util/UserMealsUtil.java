package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
       System.out.print( getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000));
//        .toLocalDate();
//        .toLocalTime();

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList,
                                                                    LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        mealList.sort(Comparator.comparing(UserMeal::getDateTime));
       /* Map<LocalDate,Integer> map = new LinkedHashMap<>();        //соответствие даты и кол-ва калорий
        for(UserMeal u:mealList){
            LocalDate ld=u.getDateTime().toLocalDate();
            map.put(ld,map.getOrDefault(ld,0)+u.getCalories());
        }

        List<UserMealWithExceed>  resultList    =new ArrayList<>(); //список приемов пищи в заданное время
        for(UserMeal u:mealList){
            if (TimeUtil.isBetween(u.getDateTime().toLocalTime(),startTime,endTime))  {
                resultList.add(new UserMealWithExceed(u,map.get(u.getDateTime().toLocalDate())>caloriesPerDay?true:false));
            }
        }
          return resultList;*/
        Map<LocalDate,Integer> map =   mealList
                .stream()
                .collect(Collectors.groupingBy((UserMeal s)-> s.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

         return mealList
                .stream()
                .filter((u)-> TimeUtil.isBetween(u.getDateTime().toLocalTime(),startTime,endTime))
                .map(s->new UserMealWithExceed(s,map.get(s.getDateTime().toLocalDate())>caloriesPerDay?true:false))
                .collect(Collectors.toList());




       

    }
}
