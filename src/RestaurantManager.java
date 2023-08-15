import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    //private String name;
    private List<Restaurant> restaurants;
    private List<Food> foodItems;
    private List<String> catagoryList;
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
        for(String str : r.getCategories()){
            if(!catagoryList.contains(str)){
                catagoryList.add(str);
            }
        }
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
        for(String str : catagoryList){
            System.out.println(str+": ");
            for(Restaurant r : restaurants){
                if(r.getCategories().contains(str)){
                    System.out.print(r.getName() + ", ");
                }
            }
            System.out.println();//new line
        }
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

     /*-------------------------
        Food Item Operations
     ----------------------------*/
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
    //3 search by category
    public void searchFoodItemsBy(String category){
        int searchIndex = -1;
        for (Food f : foodItems) {
            if (f.getName().equalsIgnoreCase(category)) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }

    //4search by category and restaurant
    public void searchFoodItemsByCatagoryAndRest(String category, String resName){
//        int searchIndex = -1;
        for (Food f : foodItems) {
            if (f.getName().equalsIgnoreCase(category) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
    }
    //5 search by price range//price is store as double in food items
    public void searchFoodItemsByPrice(int lowerBound, int upperBound){
        for (Food f : foodItems) {
            if (f.getPrice() <= upperBound && f.getPrice() >= lowerBound) {
                System.out.println(f.getName());
            }
        }
    }
    //6 search by price range and restaurant name
    public void searchFoodItemsByPriceAndRestName(int lowerBound, int upperBound, String resName){
        for (Food f : foodItems) {
            if ((f.getPrice() <= upperBound && f.getPrice() >= lowerBound) && (f.getRestaurantId() == getRestIdByName(resName))) {
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