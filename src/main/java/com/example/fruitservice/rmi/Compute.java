package com.example.fruitservice.rmi;// interface Compute

import java.rmi.Remote;
import java.rmi.RemoteException;
// Note: Interfaces cannot directly instantiate objects, including UnicastRemoteObject.
// However, you can create an implementation class for the Compute interface and instantiate UnicastRemoteObject there.

import com.example.fruitservice.rmi.Task; // Commented out or update this line if Task is in a different package
import java.rmi.server.UnicastRemoteObject;

/**
 * Defines the remote interface for a compute engine capable of executing tasks.
 */
public interface Compute extends Remote {
    /**
     * Executes the given task and returns its result.
     * 
     * @param <T> The type of the result returned by the task.
     * @param t   The task to be executed.
     * @return The result of the task execution.
     * @throws RemoteException if a remote communication error occurs.
     */
    <T> T executeTask(Task t) throws RemoteException;
}