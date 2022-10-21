package mvp.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private PropertyReader(){}

    public static String readProperties(String filePath, String parameter){
        Properties properties = null;
        try {
            InputStream input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("\tFile with path : " + filePath + " is not found\n\tMVP won’t be calculated");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("\tFile with path : " + filePath + " has error(s) occurred when reading from the input stream");
            System.exit(0);
        }
        return properties.getProperty(parameter);
    }
}