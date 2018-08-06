package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MealTestData.FIRST_MEAL.getId(), UserTestData.USER_ID);
        MealTestData.assertMatch(meal,MealTestData.FIRST_MEAL );
    }

    @Test
    public void delete() throws Exception {
        service.delete(FIRST_MEAL.getId(), UserTestData.USER_ID);
        assertMatch(service.getAll(UserTestData.USER_ID), MEALS);
    }


    @Test(expected = NotFoundException.class)
    public void deleteMealOfAnotherUser() {
        service.delete(FIRST_MEAL.getId(), UserTestData.ADMIN_ID);
    }



    @Test
    public void getAll() throws Exception {
        List<Meal> meals = service.getAll(UserTestData.USER_ID);
        assertMatch(MEALS, meals);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(FIRST_MEAL.getDateTime(),
                FIRST_MEAL.getDescription(), FIRST_MEAL.getCalories());
        updated.setDescription("UpdatedName");
        updated.setCalories(330);
        service.update(updated,UserTestData.USER_ID);
        assertMatch(service.get(FIRST_MEAL.getId(),UserTestData.USER_ID), updated);
    }

    @Test
    public void create() {

    }
}