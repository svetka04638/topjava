package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoImpl implements BasicDao {
    private ConcurrentMap<Integer, Meal> map = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private int getNextId(){
        return counter.incrementAndGet();
    }

    private int getCurrentId(){
        return counter.get();
    }

    @Override
    public void create(Object o) {
        Meal meal = (Meal) o;
        meal.setId(getNextId());
        map.put(getCurrentId(), meal);
    }

    @Override
    public List<Meal> read() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void update(Object o) {
        Meal meal = (Meal) o;
        map.replace(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return map.get(id);
    }
}
