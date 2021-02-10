package by.bsuir.java.client.controller;

import by.bsuir.java.client.exception.NegativeCostException;
import by.bsuir.java.client.service.ClientService;
import by.bsuir.java.entity.Apartment;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable{

    public Text errorLabel;
    public TextField costTextArea;
    public ListView<Apartment> apartmentsListView;

    private ClientService clientService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientService = new ClientService();
        errorLabel.setText("");
        clientService.establishConnection();
    }

    public void shutdown(){
        clientService.closeConnection();
    }

    public void findApartments(ActionEvent actionEvent) {

        try {
            //We read the cost
            double cost = Double.parseDouble(costTextArea.getText());

            if (cost < 0){
                throw new NegativeCostException("Cost can not be negative");
            }

            errorLabel.setText("");

            //And send it to the server
            List<Apartment> serverAnswer = clientService.sendCostToServer(cost);
            apartmentsListView.getItems().setAll(serverAnswer);

        }catch (NegativeCostException | NumberFormatException | IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }
}
