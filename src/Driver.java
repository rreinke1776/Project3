import java.io.IOException;

/**
 * 
 * Driver Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */

public class Driver
{
    /**
     * Main method for Project 3
     * 
     * @param args main string thing
     * @throws IOException throws when I/O exception for parsing
     */

    public static void main(String[] args) throws IOException
    {
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;

        final String directory = "data";
        // directory that files are in
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

        mapData.parseFile();
        System.out.println(mapData);
        // calling parse file & printing the result
    }
}