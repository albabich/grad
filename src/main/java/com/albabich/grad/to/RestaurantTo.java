package com.albabich.grad.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return votes == that.votes && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, votes);
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
