package com.albabich.grad.util;

import com.albabich.grad.model.Restaurant;
import com.albabich.grad.to.RestaurantTo;

import java.util.Collection;
import java.util.List;

public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .toList();
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(),restaurant.getName());
    }
}
