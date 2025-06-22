package com.example.fruitservice.tasks;

public class CalculateFruitPriceTest {
    public static void main(String[] args) {
        System.out.println("[LOG] Starting CalculateCostTest...");

        // Create the task
        CalculateCost task = new CalculateCost("Orange", 3, 20.0, "CASHIER123");
        System.out.println("[LOG] Created CalculateCost task.");

        // Test getters
        String fruitName = task.getFruitName();
        int quantity = task.getQuantity();
        double amountGiven = task.getAmountGiven();
        String cashierId = task.getCashierId();

        System.out.println("[LOG] getFruitName() returned: " + fruitName);      // Should print "Orange"
        System.out.println("[LOG] getQuantity() returned: " + quantity);        // Should print 3
        System.out.println("[LOG] getAmountGiven() returned: " + amountGiven);  // Should print 20.0
        System.out.println("[LOG] getCashierId() returned: " + cashierId);      // Should print "CASHIER123"

        // Test execute method
        String executeResult = task.execute();
        System.out.println("[LOG] execute() returned: " + executeResult);       // Should print request message

        System.out.println("[LOG] CalculateCostTest completed.");
    }
}