import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    //private String name;
    private List<Restaurant> restaurants;
    private List<Food> foodItems;
    private List<String> catagoryList;
    public static int foodItemsAdded;
    public static int restaurantAdded;


    //setters and getters

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    //constructors
    RestaurantManager(){
        this.restaurants = new ArrayList<>();
        this.foodItems = new ArrayList<>();
        this.catagoryList = new ArrayList<>();
    }
//    //add food
//    public void addFood(Food f){
//        foodItems.add(f);
//        foodItemsAdded++;
//    }
    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        restaurant operations
     -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

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


    public int searchRestaurantByName(String name){
        int searchIndex = -1;
        List<String> ans = new ArrayList<>();
        for(int i = 0; i<restaurants.size(); i++){
            Restaurant r = restaurants.get(i);
            if(r.getName().equalsIgnoreCase(name)){
                searchIndex = i;
                return searchIndex;
            }

        }
        return -1;
    }

    //restaurant search by score
    public List<String> searchRestaurantsByScore(int lowerScore, int upperScore){
        List<String> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getScore() >= lowerScore && r.getScore() <= upperScore) {
//                System.out.println(r.getName());
                ans.add(r.getName());
            }
        }
        return ans;
    }
    //restaurant search by category
    public List<String> searchRestaurantsByCategory(String categoryName){
        List<String> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.hasCategory(categoryName)) {
                ans.add(r.getName());
            }
        }
        return ans;
    }
    //restaurant search by price
    public List<String> searchRestaurantsByPrice(String price){
        List<String> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getPrice().equalsIgnoreCase(price)) {
                ans.add(r.getName());
            }
        }
        return ans;
    }
    //search restaurants by zipcode
    public List<String> searchRestaurantsByZipcode(String zipcode){
        List<String> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getZipcode().equals(zipcode)) {
                //print the names that match the code
                ans.add(r.getName());
            }
        }
        return ans;
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
    public List<String> searchFoodItemsByName(String foodName){
        List<String> ans = new ArrayList<>();
        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(foodName)) {
                //print all the names with the matching output
                ans.add(f.getName());
            }
        }//itemName.toLowerCase().contains(query.toLowerCase())
        return ans;
    }

    //2
    public List<String> searchFoodItemsByNameAndRest(String foodName, String resName){
        List<String> ans = new ArrayList<>();
        for (Food f : foodItems) {
            //if name matches check for the resName if it matches too print it
            if (f.getName().toLowerCase().contains(foodName) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                System.out.println(f.getName());
            }
        }
        return ans;
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