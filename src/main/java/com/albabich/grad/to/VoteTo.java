package com.albabich.grad.to;

import java.time.LocalDate;

public class VoteTo extends BaseTo{

    private final int  restaurantId;

    public VoteTo(int id,  int restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }



    public int getRestaurantId() {
        return restaurantId;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

