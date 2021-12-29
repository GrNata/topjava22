package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, START_SEQ);
        assertMatch(meal, meal1);
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, START_SEQ);
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1_ID, START_SEQ));
    }

    @Test
    public void getBetweenInclusive() {
    }

    @Test
    public void getAll() {
        assertMatch(meals, service.getAll(START_SEQ));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, START_SEQ);
        assertMatch(service.get(MEAL1_ID, START_SEQ), getUpdated());
    }

    @Test
    public void create() {
    }
}