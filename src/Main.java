
import com.Engeto.Restaurant.dishmanager.CookBook;
import com.Engeto.Restaurant.dishmanager.Dish;
import com.Engeto.Restaurant.exceptions.DishException;
import com.Engeto.Restaurant.ordermanager.Order;
import com.Engeto.Restaurant.ordermanager.OrderManager;
import com.Engeto.Restaurant.restaurantmanagement.RestaurantManager;
import com.Engeto.Restaurant.settings.Settings;

public class Main {
    public static void main(String[] args) {
        CookBook cookBook = new CookBook();
        Order order = new Order();
        OrderManager ordermanager = new OrderManager();
        RestaurantManager restaurantManager = new RestaurantManager();


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
        saveDishesAndOrderstoFiles(cookBook, ordermanager);

        //6.Opetovne spustenie


    }

    private static void saveDishesAndOrderstoFiles(CookBook cookBook, OrderManager ordermanager) {
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
        restaurantManager.printOrdersForTable(15);
    }

    private static void pripareDataForTesting(CookBook cookBook, Order order) {
        try {
            cookBook.addDishObj(new Dish("Kureci rizek obalovany 150g", 6.50));
            cookBook.addDishObj(new Dish("Hranolky 150g", 3.50, 15));
            cookBook.addDishObj(new Dish("Pstruh na vine 200g", 12.50, 25));
            cookBook.addDishObj(new Dish("Kofola 0,5l", 3.00, 5));
        } catch (DishException e) {
            throw new RuntimeException(e);
        }
        order.addOrder(new Order(15, 1, 2));
        order.addOrder(new Order(15, 2, 2));
        order.addOrder(new Order(15, 4, 2));

        order.addOrder(new Order(2, 4, 3));
        order.addOrder(new Order(2, 2, 3));
        order.addOrder(new Order(2, 3, 3));
    }

    private static void loadDataFromExistFiles(OrderManager ordermanager) {
        try {
            CookBook.loadDishesFromFile(Settings.fileDishesForLoad());
        } catch (DishException e) {
            System.err.println("Chyba pri cteni ze souboru : " + e.getLocalizedMessage());
        }
        ordermanager.loadOrdersFromFile(Settings.fileOrdersForLoad());
    }


}
