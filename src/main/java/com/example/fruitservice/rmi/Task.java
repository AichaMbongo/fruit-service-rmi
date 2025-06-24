package com.example.fruitservice.rmi;

import java.io.Serializable;

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
