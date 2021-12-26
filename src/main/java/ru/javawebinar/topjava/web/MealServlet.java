package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
        private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals.jsp");

        List<MealTo> mealTos = filteredByStreams(meals,  DEFAULT_CALORIES_PER_DAY);

        for (MealTo m : mealTos) {
            System.out.println(m);
        }
        req.setAttribute("meals", mealTos);

//        resp.sendRedirect("meals.jsp");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
