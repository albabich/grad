package com.albabich.grad.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1)
    private int price;

    public MenuItem() {
    }

    public MenuItem(MenuItem menuItem1) {
        this(menuItem1.id, menuItem1.date, menuItem1.name, menuItem1.price);
    }

    public MenuItem(Integer id, String name, int price) {
        this(id, now(), name, price);
    }

    public MenuItem(Integer id, LocalDate date, String name, int price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
