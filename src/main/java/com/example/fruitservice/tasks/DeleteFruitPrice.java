package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;

/**
 * Task object for deleting a fruit from the system.
 * Implements Task<String> for RMI execution and Serializable for network transfer.
 */
public class DeleteFruitPrice implements Task<String>, Serializable {
    private final String fruitName;

    public DeleteFruitPrice(String fruitName) {
        this.fruitName = fruitName;
    }

    /**
     * Executes the task (required by Task interface).
     * NOTE: The actual deletion happens in FruitComputeEngine,
     * this just returns a descriptive message.
     *
     * @return Status message (does not throw exceptions)
     */
    @Override
    public String execute() {
        return "Request to delete " + fruitName;
    }

    public String getFruitName() {
        return fruitName;
    }
}