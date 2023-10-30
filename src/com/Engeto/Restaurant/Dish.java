package com.Engeto.Restaurant;

public class Dish {
    private String title;
    private double price;
    private int preparationTime;
    private String image ;     //"bolonske-spagety-01";

//region constructors
    public Dish(String title, double price, int preparationTime, String image) throws DishException {
        this.title = title;
        this.price = price;
        if (preparationTime <= 0) {
            throw new DishException("Neplatne cislo: " + preparationTime);
        }
        this.preparationTime = preparationTime;
        this.image = image;
    }

    public Dish(String title, double price, int preparationTime) throws DishException {
        this(title,price,preparationTime,"blank");
    }

//endregion



    //region Getter Setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    //endregion



}
