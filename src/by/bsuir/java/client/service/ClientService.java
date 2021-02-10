package by.bsuir.java.client.service;

import by.bsuir.java.entity.Apartment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientService {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket clientSocket;

    public void establishConnection(){
        try {
            System.out.println("Connecting to the server....");
            clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("Successfully connected....");

            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            objectOutputStream.writeObject(new double[0]);
            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Apartment> sendCostToServer(double cost) throws IOException, ClassNotFoundException {

        objectOutputStream.writeObject(cost);

        List<Apartment> apartments = (List<Apartment>) objectInputStream.readObject();
        for (Apartment apartment: apartments
             ) {
            System.out.println(apartment);
            System.out.println();
        }
        System.out.println("---------------------------");
        return apartments;
    }
}
