package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;


public class Meal extends AbstractBaseEntity  {


    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Meal( LocalDateTime dateTime, String description, int calories) {

        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime(){return dateTime.toLocalTime();}


    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
