import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    //private String name;
    private List<Restaurant> restaurants;
    private List<Food> foodItems;
    private ArrayList<String> catagoryList;
    public static int foodItemsAdded;
    public static int restaurantAdded;


    //constructors
    RestaurantManager(){
        this.restaurants = new ArrayList<>();
        this.foodItems = new ArrayList<>();
        this.catagoryList = new ArrayList<>();
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        restaurant operations
     -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    //add food
    public void addFood(Food f){
        foodItems.add(f);
        foodItemsAdded++;
    }
    //addRestaurant
    public void addRestaurant(Restaurant r){
        //check conditions if the restaurant already exists
        restaurants.add(r);
        restaurantAdded++;
    }
    //restaurant search by name
    public void searchRestaurantByName(String name){
        int searchIndex = -1;
        for(int i = 0; i<restaurants.size(); i++){
            Restaurant r = restaurants.get(i);
            if(r.getName().equalsIgnoreCase(name)){
                searchIndex = i;
                break;
            }
        }
        if(searchIndex != -1){
            restaurants.get(searchIndex).display();
        }
    }
    //restaurant search by score
    public void searchRestaurantsByScore(int lowerScore, int upperScore){
        for (Restaurant r : restaurants) {
            if (r.getScore() >= lowerScore && r.getScore() <= upperScore) {
                System.out.println(r.getName());
            }
        }

    }
    //restaurant search by category
    public void searchRestaurantsByCategory(String categoryName){
        int searchIndex = -1;
        for (Restaurant r : restaurants) {
            if (r.hasCategory(categoryName)) {
                System.out.println(r.getName());
            }
        }
    }
    //restaurant search by price
    public void searchRestaurantsByPrice(String price){
        for (Restaurant r : restaurants) {
            if (r.getPrice().equalsIgnoreCase(price)) {
                System.out.println(r.getName());
            }
        }
    }
    //search restaurants by zipcode
    public void searchRestaurantsByZipcode(String zipcode){
        for (Restaurant r : restaurants) {
            if (r.getZipcode().equals(zipcode)) {
                //print the names that match the code
                System.out.println(r.getName());
            }
        }
    }

    //display category wise restaurant names
    public void displayCategoryWiseNames(){

    }



    //return id by rest name
    public int getRestIdByName(String restName){
        for (Restaurant r : restaurants) {
            if (r.getName().equalsIgnoreCase(restName)) {
                return r.getId();
            }
        }
        return -1;
    }

    //search menu items
    //1
    public void searchFoodItemsByName(String foodName){
        int searchIndex = -1;
        for(int i = 0; i<restaurants.size(); i++){
            Food f = foodItems.get(i);
            if(f.getName().equalsIgnoreCase(foodName)){
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }

    //2
    public void searchFoodItemsByNameAndRest(String foodName, String resName){
        int searchIndex = -1;
        for (Food f : foodItems) {
            //if name matches check for the resName if it matches too print it
            if (f.getName().equalsIgnoreCase(foodName) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }
    //3 search by catagory
    public void searchFoodItemsBy(String catagory){
        int searchIndex = -1;
        for (Food f : foodItems) {
            if (f.getName().equalsIgnoreCase(catagory)) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }

    //4search by catagory and restaurant
    public void searchFoodItemsByCatagoryAndRest(String catagory, String resName){
//        int searchIndex = -1;
        for (Food f : foodItems) {
            if (f.getName().equalsIgnoreCase(catagory) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }
    //5 search by price range//price is store as double in fooditems
    public void searchFoodItemsByPrice(int lowerbound, int upperbound){
        for (Food f : foodItems) {
            if (f.getPrice() <= upperbound && f.getPrice() >= lowerbound) {
                System.out.println(f.getName());
            }
        }
    }
    //6 search by price range and restaurant name
    public void searchFoodItemsByPriceAndRestName(int lowerbound, int upperbound, String resName){
        for (Food f : foodItems) {
            if ((f.getPrice() <= upperbound && f.getPrice() >= lowerbound) && (f.getRestaurantId() == getRestIdByName(resName))) {
                System.out.println(f.getName());
            }
        }
    }
    //7 display costliest food items
    public void displayCostliestFoodItems(String resName){
        int costliest = 0;
        int resId = getRestIdByName(resName);

        for(int i = 0; i < foodItems.size(); i++){
            Food f = foodItems.get(i);
            if(f.getPrice() > foodItems.get(costliest).getPrice()){
                costliest = i;
            }
        }

        double cost = foodItems.get(costliest).getPrice();
        //display all the costliest items
        for(Food f: foodItems){
            if(f.getPrice() == cost){
                System.out.println(f.getName());
            }
        }

    }
    //8 display number of food items in each restaurant
    public void displayTotalNumberOfFoodItems(){
        int cnt;
        for(Restaurant r : restaurants){
            cnt = 0;//set cnt = 0 for each restaurant
            System.out.print(r.getName()+ ": ");
            int resId = r.getId(); // store the id
            for(var f: foodItems){
                if(f.getRestaurantId() == resId){
                    cnt++; //when id matches increase the food cnt
                }
            }
            System.out.println(cnt); //print the food cnt
        }

    }

}