package com.example.level1;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private int activityCounter = 0;

    public int getActivityCounter() {
        return activityCounter;
    }

    public void setActivityCounter(int counter) {
        activityCounter = counter;
    }
}