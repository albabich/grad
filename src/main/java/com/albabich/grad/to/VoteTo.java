package com.albabich.grad.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class VoteTo extends BaseTo {
    @Range(min = 0)
    @NotNull
    private Integer restaurantId;

    public VoteTo() {
    }

    public VoteTo(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
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
