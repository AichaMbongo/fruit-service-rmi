package com.example.fruitservice.tasks;

public class DeleteFruitPriceTest {
    public static void main(String[] args) {
        System.out.println("[LOG] Starting DeleteFruitPriceTest...");

        // Create the task
        DeleteFruitPrice task = new DeleteFruitPrice("Banana");
        System.out.println("[LOG] Created DeleteFruitPrice task.");

        // Test getter
        String fruitName = task.getFruitName();
        System.out.println("[LOG] getFruitName() returned: " + fruitName); // Should print "Banana"

        // Test execute method
        String executeResult = task.execute();
        System.out.println("[LOG] execute() returned: " + executeResult);   // Should print request message

        System.out.println("[LOG] DeleteFruitPriceTest completed.");
    }
}