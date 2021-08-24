package com.albabich.grad.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 200)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Vote(Integer id, LocalDate date, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
