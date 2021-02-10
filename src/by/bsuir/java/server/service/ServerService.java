package by.bsuir.java.server.service;

import by.bsuir.java.entity.Apartment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServerService {

    private static final String DEFAULT_FILEPATH = "src/by/bsuir/java/server/resource/apartments.txt";

    public List<Apartment> readApartmentsFromFile(String path){
        List<String> textLines;
        List<Apartment> apartments = new ArrayList<>();
        Path filePath = Paths.get(path);
        try {
            if (Files.exists(filePath)) {
                textLines = Files.readAllLines(Paths.get(path),
                        StandardCharsets.UTF_8);
            }
            else {
                textLines = Files.readAllLines(Paths.get(DEFAULT_FILEPATH),
                        StandardCharsets.UTF_8);
            }
            for (String line: textLines){
                String[] arr = line.split(" --- ");
                apartments.add(new Apartment(arr[0],Double.parseDouble(arr[1])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return apartments;
    }

}
