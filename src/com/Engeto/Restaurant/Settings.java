package com.Engeto.Restaurant;

public class Settings {
    private static final String fileItemDelimiterValue = "\t";

    private static final String fileLoadDishes = "listDishes.txt";
    private static final String fileSaveDishes = "listDishes.txt";
    private static final String fileLoadOrders = "orders.txt";
    private static final String fileSaveOrders = "orders.txt";

    public static String fileItemDelimiter() {
        return fileItemDelimiterValue;
    }

    public static String fileDishesForLoad() {
        return fileLoadDishes;
    }
    public static String fileDishesForSave() {
        return fileSaveDishes;
    }





}
