package com.Engeto.Restaurant;

public class Settings {
    private static final String fileItemDelimiterValue = "\t";

    private static final String fileLoadDishes = "listDishes.txt";
    private static final String fileSaveDishes = "listDishes.txt";
    private static final String fileLoadOrders = "orders.txt";
    private static final String fileSaveOrders = "orders.txt";

    private static final String fileDishesIsEmpty = "dishesEmpty.txt";
    private static final String fileOrdersIsEmpty = "ordersEmpty.txt";

    public static String fileItemDelimiter() {
        return fileItemDelimiterValue;
    }

    public static String fileDishesForLoad() {        return fileLoadDishes;    }
    public static String fileDishesForSave() {
        return fileSaveDishes;
    }

    public static String fileOrdersForLoad() {        return fileLoadOrders;    }
    public static String fileOrdersForSave() {
        return fileSaveOrders;
    }

    public static String fileDishesEmpty() {
        return fileDishesIsEmpty;
    }
    public static String fileOrdersEmpty() {
        return fileOrdersIsEmpty;
    }


}
