package com.example.fruitservice.rmi;

import java.io.Serializable;

public interface Task<T> extends Serializable {
    T execute();
}
// This interface defines a Task that can be executed remotely via RMI.
// It extends Serializable to allow the task to be sent over the network.