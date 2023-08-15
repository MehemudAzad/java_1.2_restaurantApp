import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    //Id,Name,Score,Price,ZipCode,Category1,Category2,Category3
    private double score;
    private int id;
    private String price;
    private String category1;
    private String category2;
    private String category3;
    private String zipcode;
    private List<String> categories;
    private List<Food> menuItems;//store the food items for the restaurants

    //constructors
    Restaurant(int id, String name, double score, String price, String zipcode,  String category1, String category2, String category3){
        menuItems = new ArrayList<>();
        categories = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipcode = zipcode;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);


    }//Id,Name,Score,Price,ZipCode,Category1,Category2,Category3
    Restaurant(int id, String name, double score, String price, String zipcode,  String category1, String category2){
        menuItems = new ArrayList<>();
        categories = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipcode = zipcode;
        this.category1 = category1;
        this.category2 = category2;
        categories.add(category1);
        categories.add(category2);
    }
    Restaurant(int id, String name, double score, String price, String zipcode,  String category1){
        menuItems = new ArrayList<>();
        categories = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipcode = zipcode;
        this.category1 = category1;
        categories.add(category1);
    }

    //getter and setters
    public String getPrice(){
        return price;
    }

    public void setMenuItems(ArrayList<Food> menuItems) {
        this.menuItems = menuItems;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }
    //add food
    public void addMenuItems(Food f){
        menuItems.add(f);//no need to keep track of size since array list
    }
    //display
    public void display(){
        System.out.println("Name: "+ name);
        System.out.println("Score: "+ score+ "Id: "+ id + "Price: "+ price);
        System.out.println("Categories available: "+ category1+", "+ category2+ ", "+category3);
        //food items available
        System.out.println("Menu Items: ");
        for (Food menuItem : menuItems) {
            menuItem.display();
        }
    }
    //has category
    public boolean hasCategory(String categoryName){
        return category1.equalsIgnoreCase(categoryName) || category2.equalsIgnoreCase(categoryName) || category3.equalsIgnoreCase(categoryName);
    }
}
