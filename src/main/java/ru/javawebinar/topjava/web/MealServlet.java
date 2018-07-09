package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.BasicDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private BasicDao mealDao = new MealDaoImpl();


    public void init(){
        mealDao.create(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealDao.create(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Обед", 500));
        mealDao.create(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500));
        mealDao.create(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Обед", 500));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action!=null) {
            int id = Integer.parseInt(request.getParameter("mealId"));
            if (action.equals("edit")) {
                mealDao.update(mealDao.getById(id));
                request.setAttribute("min", LocalTime.MIN);
                request.setAttribute("max", LocalTime.MAX);
                request.setAttribute("meal", mealDao.getById(id));
                request.getRequestDispatcher("EditPage.jsp").forward(request, response);
                return;
            } else if (action.equals("delete")) {
                mealDao.delete(id);
                response.sendRedirect(request.getContextPath()+"/meals");
                return;
            }

        }
        request.setAttribute("meals", MealsUtil.getMealsWithExceeded(mealDao.read(),2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), dateTimeFormatter);
        Meal meal = new Meal(dateTime, request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        String id = request.getParameter("id");
        if(id == null || id.isEmpty()){
            mealDao.create(meal);
        }
        else {
            meal.setId(Integer.parseInt(id));
            mealDao.update(meal);
        }
        response.sendRedirect(request.getContextPath()+"/meals");
    }

    public static List<MealWithExceed> getMealList(){
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        return MealsUtil.getMealsWithExceeded(meals, 2000);
    }
}
