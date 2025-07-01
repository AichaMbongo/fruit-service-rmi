package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;
import java.util.Map;

/**
 * Task to request the list of all fruits and their prices.
 */
public class ListFruits implements Task<Map<String, Double>>, Serializable {
    private static final long serialVersionUID = 1L;

    public ListFruits() {
        // No fields needed; this is just a request
    }

    @Override
    public Map<String, Double> execute() {
        // The actual logic will be handled in the FruitComputeEngine
        return null;
    }
}
