package com.albabich.grad.to;

import java.beans.ConstructorProperties;

public class RestaurantTo extends BaseTo{
    private String name;
    @ConstructorProperties({"id", "name"})
    public RestaurantTo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
