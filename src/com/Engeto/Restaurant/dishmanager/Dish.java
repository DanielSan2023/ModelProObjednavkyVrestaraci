package com.Engeto.Restaurant.dishmanager;


import com.Engeto.Restaurant.exceptions.DishException;

public class Dish {
    private static int nextId = 1; // Inicializujte nextId na počáteční hodnotu 1
    private int idDish;
    private String title;
    private double price;
    private int preparationTime;
    private String image;

    //region constructors
    public Dish(String title, double price, int preparationTime, String image) throws DishException {
        this.idDish = nextId++;
        this.title = title;
        this.price = price;
        checkPreparationTime(preparationTime);
        this.preparationTime = preparationTime;
        this.image = image;
    }

    public Dish(String title, double price, int preparationTime, int idDish) throws DishException {
        this.idDish = idDish;
        this.title = title;
        this.price = price;
        checkPreparationTime(preparationTime);
        this.preparationTime = preparationTime;
        this.image = "blank";
    }

    public Dish(String title, double price, int preparationTime) throws DishException {
        this(title, price, preparationTime, "blank");
    }

    public Dish(String title, double price) throws DishException {
        this(title, price, 30, "blank");
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

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    //endregion
    private void checkPreparationTime(int preparationTime) throws DishException {
        if (preparationTime <= 0) {
            throw new DishException("Neplatné číslo: " + preparationTime);
        }
    }

    @Override
    public String toString() {
        return "Dish{" +
                "title='" + title + '\'' +
                '}';
    }
}


