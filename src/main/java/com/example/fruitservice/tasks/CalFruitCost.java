// class CalFruitCost implements Task<Double>

package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;

/**
 * Task to request calculation of the total cost for a given fruit and quantity.
 * 
 * This is a data-carrying object sent from the servlet to the RMI engine.
 * The engine will use getFruitName() and getQuantity() to look up the price and compute the cost.
 */
public class CalFruitCost implements Task<Double>, Serializable {
    private final String fruitName;
    private final int quantity;

    /**
     * Constructor initializes the fruit name and quantity.
     * @param fruitName Name of the fruit to calculate cost for.
     * @param quantity Quantity of the fruit.
     */
    public CalFruitCost(String fruitName, int quantity) {
        this.fruitName = fruitName;
        this.quantity = quantity;
    }

    /**
     * The execute() method is required by the Task interface.
     * It returns a placeholder value; the actual calculation is performed by the RMI engine.
     * @return Always returns 0.0 (actual logic is in FruitComputeEngine).
     */
    @Override
    public Double execute() {
        return 0.0;
    }

    /**
     * @return The name of the fruit.
     */
    public String getFruitName() {
        return fruitName;
    }

    /**
     * @return The quantity of the fruit.
     */
    public int getQuantity() {
        return quantity;
    }
}
