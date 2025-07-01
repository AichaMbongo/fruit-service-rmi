package com.example.fruitservice.servlet;

import com.example.fruitservice.rmi.Compute;
import com.example.fruitservice.tasks.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Servlet controller for handling HTTP requests related to fruit operations.
 * Acts as the web/controller tier in the Fruit Service Engine architecture.
 * Delegates business logic to the RMI backend via task objects.
 */
@WebServlet("/fruitService")
public class FruitControllerServlet extends HttpServlet {

    // Reference to the RMI Compute engine
    private Compute engine;

    /**
     * Initializes the servlet and connects to the RMI backend engine.
     * Called once when the servlet is first loaded.
     */
    @Override
    public void init() throws ServletException {
        try {
            // Connect to the RMI registry and look up the FruitService engine
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            engine = (Compute) registry.lookup("FruitService");
        } catch (Exception e) {
            throw new ServletException("Failed to connect to RMI engine", e);
        }
    }

    /**
     * Handles HTTP GET requests.
     * Used for actions that retrieve data or perform calculations without side effects.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json");
        Gson gson = new Gson();

        try {
            // List all fruits and their prices
            if ("listFruits".equals(action)) {
                ListFruits task = new ListFruits();
                Map<String, Double> fruits = engine.executeTask(task);
                resp.getWriter().write(gson.toJson(fruits));

            // Calculate the total cost for a quantity of a fruit
            } else if ("calculateCost".equals(action)) {
                String fruitName = req.getParameter("fruitName");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                CalFruitCost task = new CalFruitCost(fruitName, quantity);
                Double totalCost = engine.executeTask(task);
                if (totalCost == -1.0) {
                    resp.getWriter().write(gson.toJson(Map.of("status", "error", "message", "Fruit not found")));
                } else {
                    resp.getWriter().write(gson.toJson(Map.of(
                        "fruitName", fruitName,
                        "quantity", quantity,
                        "totalCost", totalCost
                    )));
                }

            // Print a formatted receipt for a fruit purchase
            } else if ("printReceipt".equals(action)) {
                String fruitName = req.getParameter("fruitName");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                double amountGiven = Double.parseDouble(req.getParameter("amountGiven"));
                String cashierId = req.getParameter("cashierId");
                CalculateCost task = new CalculateCost(fruitName, quantity, amountGiven, cashierId);
                String receipt = engine.executeTask(task);
                resp.setContentType("text/plain"); // Plain text for receipt
                resp.getWriter().write(receipt);

            // Unknown or missing action
            } else {
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"Unknown action\"}");
            }
        } catch (Exception e) {
            // Handle errors and return a JSON error response
            resp.setStatus(500);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Handles HTTP POST requests.
     * Used for actions that modify the fruit-price table (add, update, delete).
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json");
        Gson gson = new Gson();

        try {
            // Add a new fruit and its price
            if ("addFruit".equals(action)) {
                String fruitName = req.getParameter("fruitName");
                double price = Double.parseDouble(req.getParameter("price"));
                AddFruitPrice task = new AddFruitPrice(fruitName, price);
                String result = engine.executeTask(task);
                resp.getWriter().write(gson.toJson(Map.of("status", "success", "message", result)));

            // Update the price of an existing fruit
            } else if ("updateFruit".equals(action)) {
                String fruitName = req.getParameter("fruitName");
                double newPrice = Double.parseDouble(req.getParameter("newPrice"));
                UpdateFruitPrice task = new UpdateFruitPrice(fruitName, newPrice);
                String result = engine.executeTask(task);
                resp.getWriter().write(gson.toJson(Map.of("status", "success", "message", result)));

            // Delete a fruit from the system
            } else if ("deleteFruit".equals(action)) {
                String fruitName = req.getParameter("fruitName");
                DeleteFruitPrice task = new DeleteFruitPrice(fruitName);
                String result = engine.executeTask(task);
                resp.getWriter().write(gson.toJson(Map.of("status", "success", "message", result)));

            // Unknown or missing action
            } else {
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"Unknown action\"}");
            }
        } catch (Exception e) {
            // Handle errors and return a JSON error response
            resp.setStatus(500);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
