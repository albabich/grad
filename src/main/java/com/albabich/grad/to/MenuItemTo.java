package com.albabich.grad.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuItemTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Range(min = 1)
    private int price;

    public MenuItemTo() {
    }

    public MenuItemTo(Integer id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public MenuItemTo(String name, int price) {
        super(null);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItemTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
