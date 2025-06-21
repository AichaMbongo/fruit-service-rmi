package com.example.fruitservice.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for executing tasks submitted by clients.
 */
public interface Compute extends Remote {
    /**
     * Executes a given task and returns the result.
     *
     * @param <T> The type of result returned by the task.
     * @param task The task to be executed.
     * @return The result of executing the task.
     * @throws RemoteException If a communication-related error occurs.
     */
    <T> T executeTask(Task<T> task) throws RemoteException;
}
