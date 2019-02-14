package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");


        if (action == null) {
            log.info("list all meals");
            List<MealTo> mealToList = MealsUtil.getFilteredWithExcessInOnePass2(MealsUtil.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
//         response.sendRedirect("meals.jsp");

            request.setAttribute("mealToList", mealToList);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        } else if (action.equals("delete")) {
            log.info("delete meal");
            Integer id = Integer.valueOf(Objects.requireNonNull(request.getParameter("id")));

            response.sendRedirect("meals.jsp");

        } else {

        }
    }
}
