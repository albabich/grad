package com.albabich.grad.to;

import java.beans.ConstructorProperties;

public class RestaurantTo extends BaseTo {
    private final String name;
    private final int votes;

    @ConstructorProperties({"id", "name", "votes"})
    public RestaurantTo(int id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", votesByDay=" + votes +
                '}';
    }
}
