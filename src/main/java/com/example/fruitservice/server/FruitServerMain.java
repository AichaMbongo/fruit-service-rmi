package com.example.fruitservice.server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.example.fruitservice.engine.FruitComputeEngine;
public class FruitServerMain {

    public static void main(String[] args) {
        try {
            // Create and export the compute engine
            FruitComputeEngine engine = new FruitComputeEngine();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FruitCompute", engine);
            System.out.println("Fruit server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
