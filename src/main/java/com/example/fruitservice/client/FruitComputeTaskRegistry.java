package com.example.fruitservice.client;

import com.example.fruitservice.rmi.Compute;
import com.example.fruitservice.tasks.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FruitComputeTaskRegistry {
    public static void main(String[] args) {
        try {
            // Connect to the RMI registry on localhost, port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote Compute engine by the name you used in FruitServerMain
            Compute engine = (Compute) registry.lookup("FruitCompute"); // or "FruitService" if that's your binding name

            Scanner scanner = new Scanner(System.in);

            System.out.println("Fruit Service RMI Test Client");
            System.out.println("-----------------------------");

            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Add fruit");
                System.out.println("2. Update fruit price");
                System.out.println("3. Delete fruit");
                System.out.println("4. Calculate fruit cost");
                System.out.println("5. Print receipt");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                switch (choice) {
                    case 1: // Add fruit
                        System.out.print("Enter fruit name: ");
                        String addName = scanner.nextLine();
                        System.out.print("Enter fruit price: ");
                        double addPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        AddFruitPrice addTask = new AddFruitPrice(addName, addPrice);
                        String addResult = engine.executeTask(addTask);
                        System.out.println("AddFruitPrice result: " + addResult);
                        break;

                    // case 2: // Update fruit price
                    //     System.out.print("Enter fruit name: ");
                    //     String updateName = scanner.nextLine();
                    //     System.out.print("Enter new price: ");
                    //     double newPrice = scanner.nextDouble();
                    //     scanner.nextLine(); // Consume newline
                    //     UpdateFruitPrice updateTask = new UpdateFruitPrice(updateName, newPrice);
                    //     String updateResult = engine.executeTask(updateTask);
                    //     System.out.println("UpdateFruitPrice result: " + updateResult);
                    //     break;

                    case 3: // Delete fruit
                         System.out.print("Enter fruit name: ");
                         String deleteName = scanner.nextLine();
                         DeleteFruitPrice deleteTask = new DeleteFruitPrice(deleteName);
                         String deleteResult = engine.executeTask(deleteTask);
                         System.out.println("DeleteFruitPrice result: " + deleteResult);
                         break;

                     case 4: // Calculate fruit cost
                         System.out.print("Enter fruit name: ");
                         String costName = scanner.nextLine();
                         System.out.print("Enter quantity: ");
                         int quantity = scanner.nextInt();
                         scanner.nextLine(); // Consume newline
                         CalFruitCost calcTask = new CalFruitCost(costName, quantity);
                         Double costResult = engine.executeTask(calcTask);
                         if (costResult == -1.0) {
                             System.out.println("Fruit not found.");
                         } else {
                             System.out.println("Total cost: " + costResult);
                         }
                         break;

                     case 5: // Print receipt
                         System.out.print("Enter fruit name: ");
                         String receiptName = scanner.nextLine();
                         System.out.print("Enter quantity: ");
                         int receiptQty = scanner.nextInt();
                         System.out.print("Enter amount given: ");
                         double amountGiven = scanner.nextDouble();
                         scanner.nextLine(); // Consume newline
                         System.out.print("Enter cashier ID: ");
                         String cashierId = scanner.nextLine();
                         CalculateCost receiptTask = new CalculateCost(receiptName, receiptQty, amountGiven, cashierId);
                         String receipt = engine.executeTask(receiptTask);
                         System.out.println("Receipt:\n" + receipt);
                         break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
