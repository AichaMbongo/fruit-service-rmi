package com.example.fruitservice.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.example.fruitservice.engine.FruitComputeEngine;

public class FruitServerMain {

    public static void main(String[] args) {
        try {
            // Create and export the compute engine
            FruitComputeEngine engine = new FruitComputeEngine();

            // Start the RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the engine to the name "FruitService" (must match the servlet lookup)
            registry.rebind("FruitService", engine);

            System.out.println("Fruit server is running and bound as 'FruitService'...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
