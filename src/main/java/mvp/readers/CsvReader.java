package mvp.readers;

import com.google.common.io.Files;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import mvp.players.Player;
import mvp.players.factory.FactoryVariation;
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
            playerList = getMatchPlayers(csvReader);
        } catch (FileNotFoundException e) {
            System.out.println("\tFile with path : " + filePath + " is not found\n\tMVP won’t be calculated");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("\tFile with path : " + filePath + " is not .csv format\n\tMVP won’t be calculated");
            System.exit(0);
        }
        return playerList;
    }

    private static List<Player> getMatchPlayers(CSVReader csvReader) throws IOException {
        List<Player> playerList = new ArrayList<>();
        Sports sport = null;
        String[] nextRecord;
        // csv data reading
        while ((nextRecord = csvReader.readNext()) != null) {
            // set factory
            if(nextRecord.length == 1){
                try {
                    sport = Sports.valueOf(nextRecord[0]);
                } catch (IllegalArgumentException e){
                    System.out.println("\t" + nextRecord[0] + " is not found in sport list\n\tMVP won’t be calculated");
                    System.exit(0);
                }
            } else
                playerList.add(FactoryVariation.playerVariation(sport,nextRecord));
        }
        return playerList;
    }
}