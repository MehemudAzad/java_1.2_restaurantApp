import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    //private String name;
    private List<Restaurant> restaurants;
    private List<Food> foodItems;
    private List<String> catagoryList;
    public static int foodItemsAdded;
    public static int restaurantAdded;



     /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        setters and getters
     -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
       constructors
    -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    RestaurantManager(){
        this.restaurants = new ArrayList<>();
        this.foodItems = new ArrayList<>();
        this.catagoryList = new ArrayList<>();
    }

    //addRestaurant
    public void addRestaurant(Restaurant r){
        //check conditions if the restaurant already exists
        restaurants.add(r);
        for(String str : r.getCategories()){
             /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        restaurant operations
     -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/if(!catagoryList.contains(str)){
                catagoryList.add(str);
            }
        }
        restaurantAdded++;
    }

    // add food
    public void addFood(Food f){
        foodItems.add(f);
        foodItemsAdded++;
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



     /*-------------------------
        Food Item Operations
     ----------------------------*/
    /*
        helper functions
    */
     //return id by rest name
     public int getRestIdByName(String restName){
         for (Restaurant r : restaurants) {
             if (r.getName().equalsIgnoreCase(restName)) {
                 return r.getId();
             }
         }
         return -1;
     }
    /*
        main functions
    */
    //1
    public List<String> searchFoodItemsByName(String foodName){
        List<String> ans = new ArrayList<>();
        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(foodName.toLowerCase())) {
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
            if (f.getName().toLowerCase().contains(foodName.toLowerCase()) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                ans.add(f.getName());
            }
        }
        return ans;
    }
    //3 search by category
    public List<String> searchFoodItemsBy(String category){
        List<String> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(category.toLowerCase())) {
                //print all the names with the matching output
                ans.add(f.getName());
            }
        }
        return ans;
    }

    //4search by category and restaurant
    public List<String> searchFoodItemsByCatagoryAndRest(String category, String resName){
        List<String> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(category.toLowerCase()) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                ans.add(f.getName());
            }
        }

        return ans;
    }
    //5 search by price range//price is store as double in food items
    public List<String> searchFoodItemsByPrice(int lowerBound, int upperBound){
        List<String> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if (f.getPrice() <= upperBound && f.getPrice() >= lowerBound) {
                ans.add(f.getName());
            }
        }
        return ans;
    }
    //6 search by price range and restaurant name
    public List<String> searchFoodItemsByPriceAndRestName(int lowerBound, int upperBound, String resName){
        List<String> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if ((f.getPrice() <= upperBound && f.getPrice() >= lowerBound) && (f.getRestaurantId() == getRestIdByName(resName))) {
                ans.add(f.getName());
            }
        }

        return ans;
    }
    //7 display costliest food items //I need to fix this function
    public List<String> displayCostliestFoodItems(String resName){
        List<String> ans = new ArrayList<>();
        double costliest = 0;
        int resId = getRestIdByName(resName);

        //find the costliest price
        for (Food f : foodItems) {
            if (f.getRestaurantId() == resId && (f.getPrice() > costliest)) {
                costliest = f.getPrice();//store the index
            }
        }
        //display all the costliest items
        for(Food f: foodItems){
            if(f.getRestaurantId() == resId && f.getPrice() == costliest){
                ans.add(f.getName());
            }
        }
        return ans;
    }
    //8 display number of food items in each restaurant
    public void displayTotalNumberOfFoodItems(){
        int cnt; //count variable
        for(Restaurant r : restaurants){ //loop through the restaurants
            cnt = 0;//set cnt = 0
            System.out.print(r.getName()+ ": ");
            int resId = r.getId(); // store the id
            for(var f: foodItems){
                if(f.getRestaurantId() == resId){
                    cnt++; //when id matches increase the food cnt
                }
            }
            System.out.println(cnt); //print the food cnt
        }
        System.out.println();//new line
    }

}