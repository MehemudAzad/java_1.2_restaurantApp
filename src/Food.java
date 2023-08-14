public class Food {
    private int restaurantId;
    private String category;
    private String name;
    private double price;//RestaurantId,Category,Name,Price


    //constructors
    Food(int restaurantId, String category, String name, double price){
        this.restaurantId = restaurantId;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    //setter and getters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    //display function
    public void display(){
        System.out.println("Name: "+ name+ ", Category: "+ category+ ", Price: "+ price);
    }



}
