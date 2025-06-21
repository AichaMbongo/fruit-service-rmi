package com.example.fruitservice.tasks;

public class AddFruitPriceTest {
    public static void main(String[] args) {
        System.out.println("[LOG] Starting AddFruitPriceTest...");

        // Create the task
        AddFruitPrice task = new AddFruitPrice("Apple", 50.0);
        System.out.println("[LOG] Created AddFruitPrice task.");

        // Test getters
        String fruitName = task.getFruitName();
        double price = task.getPrice();
        System.out.println("[LOG] getFruitName() returned: " + fruitName); // Should print "Apple"
        System.out.println("[LOG] getPrice() returned: " + price);         // Should print 50.0

        // Test execute method
        String executeResult = task.execute();
        System.out.println("[LOG] execute() returned: " + executeResult);   // Should print request message

        System.out.println("[LOG] AddFruitPriceTest completed.");
    }
}
