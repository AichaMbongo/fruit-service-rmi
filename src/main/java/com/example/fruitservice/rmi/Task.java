package com.example.fruitservice.rmi;

import java.io.Serializable;

<<<<<<< Updated upstream
public interface Task<T> extends Serializable {
    T execute();
}
// This interface defines a Task that can be executed remotely via RMI.
// It extends Serializable to allow the task to be sent over the network.
=======
/**
 * Represents a unit of work that can be executed remotely by a Compute engine.
 * 
 * @param <T> The result type returned by this task.
 */
public interface Task<T> extends Serializable {
    /**
     * Executes the logic of the task and returns the result.
     *
     * @return The result of the task execution.
     */
    T execute();
}
>>>>>>> Stashed changes
