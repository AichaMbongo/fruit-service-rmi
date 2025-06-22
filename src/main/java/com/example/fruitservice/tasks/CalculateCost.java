package com.example.fruitservice.tasks;

import com.example.fruitservice.rmi.Task;
import java.io.Serializable;

/**
 * Task object for calculating the cost of a fruit purchase and generating a receipt.
 * Implements Task<String> for RMI execution and Serializable for network transfer.
 */
public class CalculateCost implements Task<String>, Serializable {
    private final String fruitName;
    private final int quantity;
    private final double amountGiven;
    private final String cashierId;

    public CalculateCost(String fruitName, int quantity, double amountGiven, String cashierId) {
        this.fruitName = fruitName;
        this.quantity = quantity;
        this.amountGiven = amountGiven;
        this.cashierId = cashierId;
    }

    @Override
    public String execute() {
        return "Request to calculate cost for " + quantity + " x " + fruitName +
               ", amount given: " + amountGiven + ", cashier: " + cashierId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmountGiven() {
        return amountGiven;
    }

    public String getCashierId() {
        return cashierId;
    }
}