package com.example.fruitservice.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Task extends Remote {
    String executeTask(String fruitName) throws RemoteException;
}