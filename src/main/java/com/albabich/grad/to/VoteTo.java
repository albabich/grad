package com.albabich.grad.to;

import java.time.LocalDate;

public class VoteTo {

    private LocalDate date;
//    private final String userName;
    private final String restaurantName;

    public VoteTo(LocalDate date, String restaurantName) {
        this.date = date;
//        this.userName = userName;
        this.restaurantName = restaurantName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

//    public String getUserName() {
//        return userName;
//    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "date=" + date +
//                ", userName='" + userName + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
