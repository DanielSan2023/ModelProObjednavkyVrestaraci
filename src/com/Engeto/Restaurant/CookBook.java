package com.Engeto.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookBook {

  private static Map<Integer, Dish> dishes; // Použijeme HashMap pro ukládání jídel podle jejich ID

  public CookBook() {
    dishes = new HashMap<>();
  }

  public void addDish(int idDish,Dish newDish) {
    // Přidáme nové jídlo do mapy s použitím jeho ID jako klíče
    dishes.put(idDish, newDish);
  }

  public void addDish(Dish newDish) {
    int id = generateNewId();
    newDish.setIdDish(id);
    dishes.put(id, newDish);
  }
  private int generateNewId() {
    int maxId = dishes.keySet().stream().max(Integer::compare).orElse(0);
    return maxId + 1;
  }
  // Metoda pro získání jídla z knihovny jídel podle ID
  public static Dish getDishById(int dishId) {
    for (Dish dish : dishes.values()) {
      if (dish.getIdDish() == dishId) {
        return dish;
      }
    }
    return null; // Jídlo s daným ID nebylo nalezeno
  }

  public void editDish(int dishId, Dish updatedDish) {
    if (dishes.containsKey(dishId)) {
      Dish dish = dishes.get(dishId);
      dish.setTitle(updatedDish.getTitle());
      dish.setPrice(updatedDish.getPrice());
      dish.setPreparationTime(updatedDish.getPreparationTime());
      dish.setImage(updatedDish.getImage());
    }
  }

  public void removeDish(int dishId) {
    dishes.remove(dishId);
  }

  public List<Dish> getDishes() {
    // Vytvoříme seznam všech jídel a vrátíme ho
    return new ArrayList<>(dishes.values());
  }

  public void printDishes() {
    for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
      int dishId = entry.getKey();
      Dish dish = entry.getValue();
      System.out.println("ID: " + dishId);
      System.out.println("Title: " + dish.getTitle());
      System.out.println("Price: " + dish.getPrice() + " Kč");
      System.out.println("Preparation Time: " + dish.getPreparationTime() + " minutes");
      System.out.println("Image: " + dish.getImage());
      System.out.println();
    }
  }

}
