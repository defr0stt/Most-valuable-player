package mvp.readers;

import com.google.common.io.Files;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import mvp.players.Player;
import mvp.players.factory.BasketballPlayerFactory;
import mvp.players.factory.HandballPlayerFactory;
import mvp.players.factory.PlayerFactory;
import mvp.sports.Sports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private CsvReader(){}

    public static List<Player> readCsv(String filePath){
        List<Player> playerList = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(filePath);
            if(!Files.getFileExtension(filePath).equals("csv"))
                throw new IOException();
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
            // players from csv and player factory
            PlayerFactory playerFactory = null;
            String[] nextRecord;
            // csv data reading
            while ((nextRecord = csvReader.readNext()) != null) {
                // set factory
                if(nextRecord.length == 1){
                    Sports sport = null;
                    try {
                        sport = Sports.valueOf(nextRecord[0]);
                    } catch (IllegalArgumentException e){
                        System.out.println("\t" + nextRecord[0] + " is not found in sport list\n\tMVP won’t be calculated");
                        System.exit(0);
                    }
                    if(sport == Sports.BASKETBALL)
                        playerFactory = new BasketballPlayerFactory();
                    else if(sport == Sports.HANDBALL)
                        playerFactory = new HandballPlayerFactory();
                } else {
                    if(playerFactory instanceof BasketballPlayerFactory)
                        playerList.add(playerFactory
                                        .createPlayer(nextRecord[0], nextRecord[1], Integer.parseInt(nextRecord[2]),
                                                nextRecord[3],Integer.parseInt(nextRecord[4]),Integer.parseInt(nextRecord[5]),
                                                Integer.parseInt(nextRecord[6])));
                    else if(playerFactory instanceof HandballPlayerFactory)
                        playerList.add(playerFactory
                                .createPlayer(nextRecord[0], nextRecord[1], Integer.parseInt(nextRecord[2]),
                                        nextRecord[3],Integer.parseInt(nextRecord[4]),Integer.parseInt(nextRecord[5])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tFile with path : " + filePath + " is not found\n\tMVP won’t be calculated");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("\tFile with path : " + filePath + " is not .csv format\n\tMVP won’t be calculated");
            System.exit(0);
        }
        return playerList;
    }
}