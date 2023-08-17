import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {

    //private String name;
    private List<Restaurant> restaurants;
    private List<Food> foodItems;
    private List<String> catagoryList;
    public int foodItemsAdded;
    public int restaurantAdded;



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
        foodItemsAdded = 0;
        restaurantAdded = 0;
    }

    /**************************
     helper functions
     **************************/
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

    // add food
    public void addFood(Food f){
        foodItems.add(f);
        foodItemsAdded++;
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
    public boolean restaurantExistsById(int id){
        for (Restaurant r: restaurants){
            if(r.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean foodExistsInRestaurant(int id, String foodName){
        for(Food f: foodItems){
            if(f.getName().equalsIgnoreCase(foodName) && f.getRestaurantId() == id){
                return true;
            }
        }
        return false;
    }
    //write restaurants to file again
    public void writeRestaurantsToFile(String OUTPUT_FILE_NAME) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        for(Restaurant r: restaurants)
        {
            //1,KFC,4.3,$$$,98531,Chicken,Fast Food,Family Meals
            bw.write(r.getId()+","+r.getName()+","+r.getScore()+","+r.getPrice()+","+r.getZipcode()+",");

            List<String> categories = r.getCategories();
            for (int i = 0; i < categories.size(); i++) {
                String category = categories.get(i);
                bw.write(category);

                if (i < categories.size() - 1) {
                    bw.write(",");
                }
            }
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
    //write food items to file
    public void writeFoodsToFile(String OUTPUT_FILE_NAME) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        for(Food f: foodItems)
        {
            //3,Cold Drinks,Tree Top Apple Juice Box,2.65
            bw.write(f.getRestaurantId()+","+f.getCategory()+","+f.getName()+","+f.getPrice());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

     /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        restaurant operations
     -=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

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

    //2 restaurant search by score
    public List<Restaurant> searchRestaurantsByScore(double lowerScore, double upperScore){
        List<Restaurant> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getScore() >= lowerScore && r.getScore() <= upperScore) {
                ans.add(r);
            }
        }
        return ans;
    }
    //3 restaurant search by category
    public List<Restaurant> searchRestaurantsByCategory(String categoryName){
        List<Restaurant> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.hasCategory(categoryName)) {
                ans.add(r);
            }
        }
        return ans;
    }
    //4 restaurant search by price
    public List<Restaurant> searchRestaurantsByPrice(String price){
        List<Restaurant> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getPrice().equalsIgnoreCase(price)) {
                ans.add(r);
            }
        }
        return ans;
    }
    //5 search restaurants by zipcode
    public List<Restaurant> searchRestaurantsByZipcode(String zipcode){
        List<Restaurant> ans = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getZipcode().equals(zipcode)) {
                //print the names that match the code
                ans.add(r);
            }
        }
        return ans;
    }

    //6 display category wise restaurant names
    public void displayCategoryWiseNames(){
        for(String str : catagoryList){
            if(str.equalsIgnoreCase("")){
                continue;
            }
            System.out.print(str+": ");
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

    //1 searchFoodItemsByName
    public List<Food> searchFoodItemsByName(String foodName){
        List<Food> ans = new ArrayList<>();
        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(foodName.toLowerCase())) {
                //print all the names with the matching output
                ans.add(f);
            }
        }//itemName.toLowerCase().contains(query.toLowerCase())
        return ans;
    }

    //2 searchFoodItemsByNameAndRest
    public List<Food> searchFoodItemsByNameAndRest(String foodName, String resName){
        List<Food> ans = new ArrayList<>();

        for (Food f : foodItems) {
            //if name matches check for the resName if it matches too print it
            if (f.getName().toLowerCase().contains(foodName.toLowerCase()) && (f.getRestaurantId() == getRestIdByName(resName))) {
                //print all the names with the matching output
                ans.add(f);
            }
        }
        return ans;
    }
    //3 search by category
    public List<Food> searchFoodItemsByCategory(String category){
        List<Food> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if (f.getName().toLowerCase().contains(category.toLowerCase())) {
                //print all the names with the matching output
                ans.add(f);
            }
        }
        return ans;
    }

    //4search by category and restaurant
    public List<Food> searchFoodItemsByCategoryAndRest(String category, String resName){
        List<Food> ans = new ArrayList<>();
        int restId = getRestIdByName(resName);
        for (Food f : foodItems) {
            if ((f.getRestaurantId() ==restId) && f.getName().toLowerCase().contains(category.toLowerCase())) {
                //print all the names with the matching output
                ans.add(f);
            }
        }

        return ans;
    }
    //5 search by price range//price is store as double in food items
    public List<Food> searchFoodItemsByPrice(double lowerBound, double upperBound){
        List<Food> ans = new ArrayList<>();

        for (Food f : foodItems) {
            if (f.getPrice() <= upperBound && f.getPrice() >= lowerBound) {
                ans.add(f);
            }
        }
        return ans;
    }
    //6 search by price range and restaurant name
    public List<Food> searchFoodItemsByPriceAndRestName(double lowerBound, double upperBound, String resName){
        List<Food> ans = new ArrayList<>();

        int restId = getRestIdByName(resName);
        for (Food f : foodItems) {
            if ((f.getRestaurantId() == restId) && (f.getPrice() <= upperBound && f.getPrice() >= lowerBound)) {
                ans.add(f);
            }
        }
        return ans;
    }
    //7 display costliest food items //I need to fix this function
    public List<Food> displayCostliestFoodItems(String resName){
        List<Food> ans = new ArrayList<>();
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
                ans.add(f);
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