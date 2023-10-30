package com.Engeto.Restaurant;


import java.util.ArrayList;
import java.util.List;

public class CookBook {

  private static List<Dish> dishes ;

  public CookBook() { dishes = new ArrayList<>();
  }

  public static void addDish(Dish newDish){  // prida plantu do zoznamu
    dishes.add(newDish);
  }

  public void editDish(String dishName, Dish updatedDish) {
    for (Dish dish : dishes) {
      if (dish.getTitle().equals(dishName)) {
        dish.setTitle(updatedDish.getTitle());
        dish.setPrice(updatedDish.getPrice());
        dish.setPreparationTime(updatedDish.getPreparationTime());
        dish.setImage(updatedDish.getImage());
        break; // Jídlo bylo nalezeno, takže nemá smysl pokračovat ve vyhledávání.
      }
    }
  }
  public void removeDish(String dishName) {
    dishes.removeIf(dish -> dish.getTitle().equals(dishName));
  }


  public static List<Dish> getDish(){ return dishes;};




}
