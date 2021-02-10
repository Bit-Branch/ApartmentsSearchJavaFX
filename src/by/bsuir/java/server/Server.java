package by.bsuir.java.server;

import by.bsuir.java.entity.Apartment;
import by.bsuir.java.server.service.ServerService;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] arg) {

        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        List<Apartment> serverApartments = new ServerService().readApartmentsFromFile("src/by/bsuir/java/server/resource/apartments.txt");
        try {
            System.out.println("Server launching....");
            serverSocket = new ServerSocket(2525);
            System.out.println("Server is working....");
            clientAccepted = serverSocket.accept();
            System.out.println("Client connected....");

            objectInputStream = new ObjectInputStream(clientAccepted.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientAccepted.getOutputStream());

            while (true) {
                double cost = (double) objectInputStream.readObject();
                List<Apartment> filteredApartments = new ArrayList<>();
                System.out.println("Received from client: " + cost);
                for (Apartment apartment: serverApartments
                     ) {
                    if (apartment.getCost() <= cost){
                        filteredApartments.add(apartment);
                    }
                }
                objectOutputStream.writeObject(filteredApartments);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
                clientAccepted.close();
                serverSocket.close();
            } catch (IOException e ) {
                System.out.println(e.getMessage());
            }
        }
    }
}
