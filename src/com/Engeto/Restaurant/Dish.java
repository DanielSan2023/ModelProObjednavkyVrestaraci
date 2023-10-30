package com.Engeto.Restaurant;

public class Dish {
    private String Title;
    private double price;
    private int preparationTime;
    private String image ;     //"bolonske-spagety-01";


    public Dish(String title, double price, int preparationTime, String image) {
        Title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.image = image;
    }

    public Dish(String title, double price, int preparationTime) {
        this(title,price,preparationTime,"blank");
    }




}
