import java.io.Serializable;

public class AddFruitPrice implements Task<String>, Serializable {
    private String fruitName;
    private double price;

    public AddFruitPrice(String fruitName, double price) {
        this.fruitName = fruitName;
        this.price = price;
    }

    @Override
    public String execute() {
        // Here you would add logic to store the fruit and price, e.g., in a database or map.
        // For demonstration, we'll just return a confirmation string.
        return "Added " + fruitName + " with price $" + price;
    }

    public String getFruitName() {
        return fruitName;
    }

    public double getPrice() {
        return price;
    }
}