package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealToList = new ArrayList<>();

        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false));
        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, false));
        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false));
        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, true));
        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, true));
        mealToList.add(new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, true));

//         response.sendRedirect("meals.jsp");

        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
