package com.example.fruitservice.engine;

import com.example.fruitservice.rmi.Compute;
import com.example.fruitservice.rmi.Task;
import com.example.fruitservice.tasks.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

public class FruitComputeEngine extends UnicastRemoteObject implements Compute {

    private final ConcurrentHashMap<String, Double> fruitPriceTable;

    @Override
    public java.util.Map<String, Double> ListFruits() throws RemoteException {
        // Defensive copy to avoid exposing internal map
        return new java.util.HashMap<>(fruitPriceTable);
    }

    public FruitComputeEngine() throws RemoteException {
        super();
        this.fruitPriceTable = new ConcurrentHashMap<>();
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        // AddFruitPrice
        if (t instanceof AddFruitPrice) {
            AddFruitPrice task = (AddFruitPrice) t;
            String fruit = task.getFruitName();
            double price = task.getPrice();
            if (fruitPriceTable.containsKey(fruit)) {
                return (T) ("Error: Fruit '" + fruit + "' already exists.");
            }
            fruitPriceTable.put(fruit, price);
            return (T) ("Fruit '" + fruit + "' added with price " + price);
        }

        // UpdateFruitPrice
        if (t instanceof UpdateFruitPrice) {
            UpdateFruitPrice task = (UpdateFruitPrice) t;
            String fruit = task.getFruitName();
            double newPrice = task.getNewPrice();
            if (!fruitPriceTable.containsKey(fruit)) {
                return (T) ("Error: Fruit '" + fruit + "' not found for update.");
            }
            fruitPriceTable.put(fruit, newPrice);
            return (T) ("Price for '" + fruit + "' updated to " + newPrice);
        }

        // DeleteFruitPrice
        if (t instanceof DeleteFruitPrice) {
            DeleteFruitPrice task = (DeleteFruitPrice) t;
            String fruit = task.getFruitName();
            if (fruitPriceTable.remove(fruit) != null) {
                return (T) ("Fruit '" + fruit + "' deleted");
            } else {
                return (T) ("Error: Fruit '" + fruit + "' not found for deletion.");
            }
        }

        // CalFruitCost
        if (t instanceof CalFruitCost) {
            CalFruitCost task = (CalFruitCost) t;
            String fruit = task.getFruitName();
            int quantity = task.getQuantity();
            Double price = fruitPriceTable.get(fruit);
            if (price == null) {
                return (T) Double.valueOf(-1.0);
            }
            return (T) Double.valueOf(price * quantity);
        }

        // ListFruitsTask
        if (t instanceof ListFruits) {
            // Defensive copy to avoid exposing internal map
            return (T) new java.util.HashMap<>(fruitPriceTable);
        }

        // CalculateCost (for receipt)
        if (t instanceof CalculateCost) {
            CalculateCost task = (CalculateCost) t;
            String fruit = task.getFruitName();
            int quantity = task.getQuantity();
            double amountGiven = task.getAmountGiven();
            String cashierId = task.getCashierId();
            Double price = fruitPriceTable.get(fruit);
            if (price == null) {
                return (T) ("Error: Fruit '" + fruit + "' not found for receipt.");
            }
            double totalCost = price * quantity;
            double change = amountGiven - totalCost;
            StringBuilder receipt = new StringBuilder();
            receipt.append("--- RECEIPT ---\n");
            receipt.append("Cashier: ").append(cashierId).append("\n");
            receipt.append("Item: ").append(fruit).append("\n");
            receipt.append("Quantity: ").append(quantity).append("\n");
            receipt.append("Unit Price: Ksh").append(String.format("%.2f", price)).append("\n");
            receipt.append("Total Cost: Ksh").append(String.format("%.2f", totalCost)).append("\n");
            receipt.append("Amount Given: Ksh").append(String.format("%.2f", amountGiven)).append("\n");
            receipt.append("Change Due: Ksh").append(String.format("%.2f", change)).append("\n");
            receipt.append("----------------\n");
            receipt.append("Thank you!");
            return (T) receipt.toString();
        }

        throw new RemoteException("Unknown task type: " + t.getClass().getName());
    }
}
