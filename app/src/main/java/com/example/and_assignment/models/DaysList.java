package com.example.and_assignment.models;

import java.util.List;

public class DaysList {
    private List<Day> days;

    public DaysList(List<Day> days) {
        this.days = days;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
