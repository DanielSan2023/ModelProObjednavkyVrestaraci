
import com.Engeto.Restaurant.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DishException {
        CookBook cookBook = new CookBook();
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        OrderManager ordermanager = new OrderManager();
        RestaurantManager restaurantManager = new RestaurantManager();

//
//         //Vytvoření jídel
////        Dish spaghetti, steak, spaghetti2, updateDish,kofola,kofola2 = null;
////
////        Dish jedlo1;
////       try {kofola  = new Dish("Kofola malá",2,5);
////           kofola2  = new Dish("Kofola velká",3,5);
////            spaghetti = new Dish("Boloňské špagety(150g)", 12.99, 30);
////            spaghetti2 = new Dish("Sicilske špety", 12.54, 30);
////            steak = new Dish("Hovězí steak", 18.99, 45);
////
////            jedlo1 = new Dish("Kuracie nugetky", 12.30, 28);
////            Dish jedlo2 = new Dish("Kuracie stehienka", 14.30, 35);
////            Dish jedlo3 = new Dish("Hovadzie na smotane", 18.30, 35);
////            Dish jedlo4 = new Dish("Pizza Hawai (450g)", 9.30, 22);
////            Dish jedlo5 = new Dish("Pizza 4 Seasons(600g)", 10.30, 22);
////            Dish jedlo6 = new Dish("Hydinové guľky v rajčiakovej omáčke ", 14.30, 35);
////            Dish jedlo7 = new Dish("Morčací paprikáš", 15.30, 28);
////           CookBook.addDish(kofola);
////           CookBook.addDish(kofola2);
////           CookBook.addDish(jedlo1);
////           CookBook.addDish(jedlo2);
////            CookBook.addDish(jedlo3);
////            CookBook.addDish(jedlo4);
////            CookBook.addDish(jedlo5);
////            CookBook.addDish(jedlo6);
////            CookBook.addDish(jedlo7);
//
//
//
////        } catch (DishException e) {
////            throw new RuntimeException(e);
////        }
////        CookBook.addDish(steak);
////        CookBook.addDish(spaghetti);
////        CookBook.addDish(spaghetti2);
//
//
//



//        try {
//            cookBook.saveToFile(Settings.fileDishesForSave(), cookBook);
//        } catch (DishException e) {
//            throw new RuntimeException(e);
//        }
//
//

//
//

//
//
////
//
////        // vybavenie objednavky
////        order2.settingFulfilmentTime();
////        order4.settingFulfilmentTime();
//        order.markAsPaid();
////        //order.printOrdersForTable(2);
////      order4.settingFulfilmentTime();
////        order10.settingFulfilmentTime();
//
//
//        //Objednavky zo suboru
//        ordermanager.loadOrdersFromFile("orders.txt");
//
//        order.setFulfilmentTimeForOrder(8);
//
//        try {
//            order.setPaidForOrder(8);
//        } catch (DishException e) {
//            System.err.println("Chyba  s objednavkou:  "+e.getLocalizedMessage());
//        }
//
//        ordermanager.saveOrdersToFile("orders.txt");
//        // ordermanager.saveOrdersToFile("orders.txt");
//
//        order.printOrdersForTable(2);
//        order.printOrdersForTable(9);
//        order.printOrdersForTable(11);

//        ordermanager.loadOrdersFromFile(Settings.fileOrdersForLoad());


                    //TESTOVACI SCENAR
        //1.Stav evidence z disku:
        loadDataFromExistFiles(ordermanager);

        //2.Připrav testovací data:
        pripareDataForTesting(cookBook, order);

        //3.Celkova cena konzumace pro stul 15:
        order.printTotalDishPriceForTable(15);

        //4.Informace pre Management:
        printForRestaurantManagement(restaurantManager);

        //5.Uloz data na disk:
        saveDishesAndOrdertoFiles(cookBook, ordermanager);

        //6.Zoznam objednavok pre stol 15:
        order.printOrdersForTable(15);


    }

    private static void saveDishesAndOrdertoFiles(CookBook cookBook, OrderManager ordermanager) {
        try {
            cookBook.saveToFile(Settings.fileDishesForSave(), cookBook);
        } catch (DishException e) {
            throw new RuntimeException(e);
        }
        ordermanager.saveOrdersToFile(Settings.fileOrdersForSave());
    }

    private static void printForRestaurantManagement(RestaurantManager restaurantManager) {
        restaurantManager.countUnfinishedOrders();
        restaurantManager.sortOrdersByOrderTime();
        System.out.println("Priemerna doba spracovanie objednavok: " + restaurantManager.averageProcessTimeforOrders());
        restaurantManager.printTodayOrderedDishes();
    }

    private static void pripareDataForTesting(CookBook cookBook, Order order) {
        try {
            cookBook.addDishObj(new Dish("Kureci rizek obalovany 150g",6.50));
            cookBook.addDishObj(new Dish("Hranolky 150g",3.50,15));
            cookBook.addDishObj(new Dish("Pstruh na vine 200g",12.50,25));
            cookBook.addDishObj(new Dish("Kofola 0,5l",3.00,5));
        } catch (DishException e) {
            throw new RuntimeException(e);
        }
        order.addOrder(new Order(15,1,2));
        order.addOrder(new Order(15,2,2));
        order.addOrder(new Order(15,4,2));

        order.addOrder(new Order(2,4,3));
        order.addOrder(new Order(2,2,3));
        order.addOrder(new Order(2,3,3));
    }

    private static void loadDataFromExistFiles(OrderManager ordermanager) {
        try {CookBook.loadDishesFromFile(Settings.fileDishesForLoad());
        } catch (DishException e) {
            System.err.println("Chyba pri cteni ze souboru : "+e.getLocalizedMessage()); }
        ordermanager.loadOrdersFromFile(Settings.fileOrdersForLoad());
    }


}
