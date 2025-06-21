// class AddFruitPrice implements Task<String>
package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;

/**
 * Task object for adding a new fruit and its price to the system.
 * Implements Task<String> for RMI execution and Serializable for network transfer.
 */
public class AddFruitPrice implements Task<String>, Serializable {
    private final String fruitName;
    private final double price;

    public AddFruitPrice(String fruitName, double price) {
        this.fruitName = fruitName;
        this.price = price;
    }

    /**
     * Executes the task (required by Task interface).
     * NOTE: The actual data modification happens in FruitComputeEngine,
     * this just returns a descriptive message.
     * 
     * @return Status message (does not throw exceptions)
     */
    @Override
    public String execute() {  // Removed "throws Exception"
        return "Request to add " + fruitName + " with price " + price;
    }

    public String getFruitName() {
        return fruitName;
    }

    public double getPrice() {
        return price;
    }
}


// How Does Adding a Fruit Work?
// Step 1: Writing the Task Card

// When you want to add a new fruit and its price to the pantry, you fill out a task card.

// The card says:

// “Add this fruit: Apple”

// “Set its price: 50.0”

// This card is the AddFruitPrice object.

// The card is very simple: it just holds the name and price. It doesn’t actually do the adding itself.

// Step 2: Sending the Card to the Robot

// You send this card through a delivery tube (RMI) to the robot in the pantry.

// Step 3: Robot Reads and Executes

// The robot receives the card.

// It reads the instructions: “Add Apple with price 50.0.”

// The robot then updates the pantry (the fruit-price table) accordingly.

// Step 4: Reporting Back

// The robot writes a note back: “I have added Apple with price 50.0” (or an error message if something went wrong).

// You get this note as the result of the operation.


// Real-World Analogy	Java Code Component
// Task card	        AddFruitPrice object
// Robot assistant	    FruitComputeEngine
// Pantry	            fruitPriceTable map
// Delivery tube	    RMI
// Card’s instructions	fruitName, price fields
// Robot reads card	get FruitName(), getPrice()
// Robot does the work 	Engine updates the map


