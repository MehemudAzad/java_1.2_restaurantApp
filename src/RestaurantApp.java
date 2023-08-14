import java.io.*;
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

                    //do loop for searching
                    do {
                        option = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character


                        switch (option) {
                            //search by :
                            case 1:
                                //name
                                String name;
                                name = scanner.nextLine();
                                restaurantManager.searchRestaurantByName(name);
                                break;
                            case 2:
                                //score
                                int lower, upper;
                                System.out.println("Enter the lowerbound: ");
                                lower = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter the upperbound: ");
                                upper = scanner.nextInt();
                                scanner.nextLine();
                                restaurantManager.searchRestaurantsByScore(lower, upper);
                                break;
                            case 3:
                                //category
                                System.out.println("Enter the catagory: ");
                                String categoryName3;
                                categoryName3 = scanner.nextLine();
                                restaurantManager.searchRestaurantsByCategory(categoryName3);
                                break;
                            case 4:
                                //price
                                System.out.println("Enter the price: ");
//                              String
                                break;
                            case 5:
                                //zipcode
                                break;
                            case 6:
                                //different category wise
                                restaurantManager.displayCategoryWiseNames();
                                break;
                            case 7:
                                //back to main menu
                                break;
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
                        option = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        switch (option) {
                            //search food items by
                            case 1:
                                //name of food
                                foodName = scanner.nextLine();
                                restaurantManager.searchRestaurantByName(foodName);
                                break;
                            case 2:
                                //name of a given restaurant and food
                                foodName = scanner.nextLine();
                                resName = scanner.nextLine();

                                restaurantManager.searchFoodItemsByNameAndRest(foodName, resName);
                                break;
                            case 3:
                                //category of food
                                foodCategory = scanner.nextLine();
                                restaurantManager.searchRestaurantsByCategory(foodCategory);
                                break;
                            case 4:
                                //category in a given restaurant
                                foodCategory = scanner.nextLine();
                                resName = scanner.nextLine();
                                restaurantManager.searchFoodItemsByCatagoryAndRest(resName, foodCategory);
                                break;
                            case 5:
                                //by price range of food
                                upperBoundPrice = Integer.parseInt(scanner.nextLine());
                                lowerBoundPrice = Integer.parseInt(scanner.nextLine());
                                restaurantManager.searchFoodItemsByPrice(lowerBoundPrice, upperBoundPrice);
                                break;
                            case 6:
                                //by price range in a given restaurant

                                break;
                            case 7:
                                //costliest foodItems on the menu in a given restaurant
                                break;
                            case 8:
                                //list of restaurants and their total food Items
                                break;
                            case 9:
                                //return to main menu
                                break;
                        }
                    } while (option != 9);
                }
                case 3 -> {
                    //add restaurants
//                    int Id,string Name,double Score,string Price,string ZipCode,Category1,Category2,Category3
//                    System.out.println("Enter the id of restaurant: ");
//                    int id = scanner.nextInt();
//                    scanner.nextLine();
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
