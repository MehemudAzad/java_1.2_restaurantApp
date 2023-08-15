import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {

    private static final String INPUT_FILE_NAME = "restaurant.txt";
    private static final String INPUT_FILE_NAME2 = "menu.txt";
    
    //main
    public static void main(String[] args) throws IOException {
        //take input
        RestaurantManager restaurantManager = new RestaurantManager();
        //declare scanner
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String [] array = line.split(",", -1);
            if(array.length == 8){
                Restaurant r = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6], array[7]);
                restaurantManager.addRestaurant(r);
            }else if(array.length == 7){
                Restaurant r = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6]);
                restaurantManager.addRestaurant(r);
            }else if(array.length == 6){
                Restaurant r = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5]);
                restaurantManager.addRestaurant(r);
            }
        }
        br.close();

        br = new BufferedReader(new FileReader(INPUT_FILE_NAME2));

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String[] array = line.split(",", -1);
            if (array.length == 4) {
                Food f = new Food(Integer.parseInt(array[0]), array[1], array[2], Double.parseDouble(array[3]));
                restaurantManager.addFood(f);
            }
        }
        br.close();

        int choice;
        int option;

        do{
            System.out.println("Main menu: ");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    //search by restaurants
                    do {
                        String name;
                        String price;
                        String zipcode;
                        String category;
                        int lower, upper;
                        List<String> results;

                        System.out.println("Restaurant Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Score");
                        System.out.println("3) By Category");
                        System.out.println("4) By Price");
                        System.out.println("5) By Zip Code");
                        System.out.println("6) Different Category Wise List of Restaurants");
                        System.out.println("7) Back to Main Menu");


                        option = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (option) {
                            //search by :
                            case 1 -> {
                                //name
                                name = scanner.nextLine();
                                int index = restaurantManager.searchRestaurantByName(name);
                                if (index != -1) {
                                    String resName = restaurantManager.getRestaurants().get(index).getName();
                                    System.out.println(resName);
                                } else {
                                    System.out.println("No such restaurant with this name");
                                }
                            }
                            case 2 -> {
                                //score
                                System.out.println("Enter the lower bound: ");
                                lower = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter the upper bound: ");
                                upper = scanner.nextInt();
                                scanner.nextLine();
                                results = restaurantManager.searchRestaurantsByScore(lower, upper);
                                //print it
                                if (results.isEmpty()) {
                                    System.out.println("No results found for the score range.");
                                } else {
                                    System.out.println("Restaurants within the score range are: ");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 3 -> {
                                //category
                                System.out.println("Enter the category: ");
                                category = scanner.nextLine();
                                results = restaurantManager.searchRestaurantsByCategory(category);
                                //print it
                                if (results.isEmpty()) {
                                    System.out.println("No results found for '" + category+  "'.");
                                } else {
                                    System.out.println("Search results for '" + category + "':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 4 -> {
                                //by price
                                price = scanner.nextLine();
                                System.out.println("Enter the price: ");
                                results = restaurantManager.searchRestaurantsByPrice(price);
                                //print it
                                if (results.isEmpty()) {
                                    System.out.println("No results found for price '" + price + "'.");
                                } else {
                                    System.out.println("Search results for price '" + price + "':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 5 -> {
                                //by zipcode;
                                zipcode = scanner.nextLine();
                                System.out.println("Enter the zipcode: ");
                                results = restaurantManager.searchRestaurantsByZipcode(zipcode);
                                //print it
                                if (results.isEmpty()) {
                                    System.out.println("No results found for zipcode '" + zipcode + "'.");
                                } else {
                                    System.out.println("Search results for zipcode '" + zipcode + "':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 6 -> {
                                //different category wise
                                System.out.println("Displaying restaurants category wise: ");
                                restaurantManager.displayCategoryWiseNames();
                            }
                            case 7 ->
                                //back to main menu
                                    System.out.println("Going back to Main Menu.");
                        }
                    } while (option != 7);
                }
                //break statement for case 1 outer
                case 2 -> {
                    //search food items
                    do {
                        String foodName;
                        String resName;
                        String foodCategory;
                        int upperBoundPrice;
                        int lowerBoundPrice;
                        List<String> results;

                        /*Food Item Searching Options:
                        1) By Name
                        2) By Name in a Given Restaurant
                        3) By Category
                        4) By Category in a Given Restaurant
                        5) By Price Range
                        6) By Price Range in a Given Restaurant
                        7) Costliest Food Item(s) on the Menu in a Given Restaurant
                        8) List of Restaurants and Total Food Item on the Menu
                        9) Back to Main Menu*/

                        System.out.println("Food Item Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurant");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant");
                        System.out.println("5) By Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");

                        option = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        switch (option) {
                            //search food items by
                            case 1 -> {
                                //name of food
                                foodName = scanner.nextLine();
                                results = restaurantManager.searchFoodItemsByName(foodName);
                                if (results.isEmpty()) {
                                    System.out.println("No results found for '" + foodName + "'.");
                                } else {
                                    System.out.println("Search results for '" + foodName + "':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 2 -> {
                                //name of a given restaurant and food
                                foodName = scanner.nextLine();
                                resName = scanner.nextLine();
                                results = restaurantManager.searchFoodItemsByNameAndRest(foodName, resName);
                                //print
                                if (results.isEmpty()) {
                                    System.out.println("No such food item with this name on the menu of this restaurant");
                                } else {
                                    System.out.println("Search results for '" + foodName +"' in '"+resName +"':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 3 -> {
                                //category of food
                                foodCategory = scanner.nextLine();
                                results = restaurantManager.searchRestaurantsByCategory(foodCategory);
                                //print
                                if (results.isEmpty()) {
                                    System.out.println("No such food item with this category");
                                } else {
                                    System.out.println("Search results for '" + foodCategory +"':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 4 -> {
                                //category in a given restaurant
                                foodCategory = scanner.nextLine();
                                resName = scanner.nextLine();
                                results = restaurantManager.searchFoodItemsByCatagoryAndRest(resName, foodCategory);
                                //print
                                if (results.isEmpty()) {
                                    System.out.println("“No such food item with this category on the menu of this restaurant");
                                } else {
                                    System.out.println("Search results for '" + foodCategory +"':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 5 -> {
                                //by price range of food
                                upperBoundPrice = Integer.parseInt(scanner.nextLine());
                                lowerBoundPrice = Integer.parseInt(scanner.nextLine());
                                results = restaurantManager.searchFoodItemsByPrice(lowerBoundPrice, upperBoundPrice);
                                //print
                                if (results.isEmpty()) {
                                    System.out.println("No such food item with this price range.");
                                } else {
                                    System.out.println("Search results for the price range'" + lowerBoundPrice + "-" +upperBoundPrice +"':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 6 -> {
                                //by price range in a given restaurant
                                upperBoundPrice = Integer.parseInt(scanner.nextLine());
                                lowerBoundPrice = Integer.parseInt(scanner.nextLine());
                                resName = scanner.nextLine();
                                results = restaurantManager.searchFoodItemsByPriceAndRestName(lowerBoundPrice, upperBoundPrice, resName);
                                //print
                                if (results.isEmpty()) {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                } else {
                                    System.out.println("Search results for this price range in '" + resName +"':");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 7 -> {
                                //costliest foodItems on the menu in a given restaurant
                                resName = scanner.nextLine();
                                results = restaurantManager.displayCostliestFoodItems(resName);

                                if (results.isEmpty()) {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                } else {
                                    System.out.println("The costliest food items in '" + resName +"' is/are:");
                                    for (String result : results) {
                                        System.out.println(result);
                                    }
                                }
                            }
                            case 8 ->{
                                //list of restaurants and their total food Items
                                System.out.println("The total food items on the menu for every restaurant");
                                restaurantManager.displayTotalNumberOfFoodItems();
                            }
                            case 9 ->
                                //return to main menu
                                    System.out.println("Going back to main menu.");
                        }
                    } while (option != 9);
                }
                case 3 -> {
                    //add restaurants
//                    int id,string Name,double Score,string Price,string ZipCode,Category1,Category2,Category3
                    Restaurant r = null;
                    System.out.println("Enter the name of restaurant: ");
                    String resName = scanner.nextLine();
                    System.out.println("Enter the name of restaurant: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter the price of restaurant: ");
                    String price = scanner.nextLine();
                    System.out.println("Enter the zipcode of restaurant: ");
                    String foodZipcode = scanner.nextLine();
                    //take the number of categories and use if else to handle cases
                    int cnt;
                    System.out.println("Enter the number of categories: ");
                    cnt = scanner.nextInt();
                    String category1, category2, category3;
                    if (cnt == 1) {
                        System.out.println("Enter the name of category1: ");
                        category1 = scanner.nextLine();
                        System.out.println("Enter the name of category2: ");
                        category2 = scanner.nextLine();
                        System.out.println("Enter the name of category3: ");
                        category3 = scanner.nextLine();

                        r = new Restaurant(RestaurantManager.restaurantAdded + 1, resName, score, price, foodZipcode, category1, category2, category3);

                    } else if (cnt == 2) {
                        System.out.println("Enter the name of category1: ");
                        category1 = scanner.nextLine();
                        System.out.println("Enter the name of category2: ");
                        category2 = scanner.nextLine();

                        r = new Restaurant(RestaurantManager.restaurantAdded + 1, resName, score, price, foodZipcode, category1, category2);

                    } else if (cnt == 3) {
                        System.out.println("Enter the name of category1: ");
                        category1 = scanner.nextLine();

                        r = new Restaurant(RestaurantManager.restaurantAdded + 1, resName, score, price, foodZipcode, category1);

                    }
                    restaurantManager.addRestaurant(r);
                }
                case 4 -> {
                    //add food item
                    //RestaurantId,Category,Name,Price
                    Food f = null;
                    System.out.println("Enter the name of food item: ");
                    String foodName = scanner.nextLine();
                    System.out.println("Enter the name of category: ");
                    String categoryName = scanner.nextLine();
                    System.out.println("Enter the price of restaurant: ");
                    double foodPrice = Double.parseDouble(scanner.nextLine());
                    f = new Food(RestaurantManager.foodItemsAdded + 1, categoryName, foodName, foodPrice);
                    restaurantManager.addFood(f);
                }
                case 5 -> System.out.println("Exiting system.");

//                    BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
//                    bw.write(text);
//                    bw.write(System.lineSeparator());
//                    bw.close();
                //write back into the files
            }

        }while(choice!=5);

        scanner.close();
    }
}
