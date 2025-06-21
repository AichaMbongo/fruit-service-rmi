// class UpdateFruitPrice implements Task<String>
package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;

/**
 * Task to request updating the price of an existing fruit.
 * 
 * This object is sent from the servlet to the RMI engine, which uses
 * getFruitName() and getNewPrice() to perform the update.
 */
public class UpdateFruitPrice implements Task<String>, Serializable {
    private final String fruitName;
    private final double newPrice;

    /**
     * Constructor initializes the fruit name and the new price.
     * @param fruitName Name of the fruit to update.
     * @param newPrice New price to set for the fruit.
     */
    public UpdateFruitPrice(String fruitName, double newPrice) {
        this.fruitName = fruitName;
        this.newPrice = newPrice;
    }

    /**
     * The execute() method is required by the Task interface.
     * It returns a descriptive message; the actual update is performed by the RMI engine.
     * @return Status message for logging or debugging.
     */
    @Override
    public String execute() {
        return "Request to update " + fruitName + " to new price " + newPrice;
    }

    /**
     * @return The name of the fruit to update.
     */
    public String getFruitName() {
        return fruitName;
    }

    /**
     * @return The new price to set for the fruit.
     */
    public double getNewPrice() {
        return newPrice;
    }
}
