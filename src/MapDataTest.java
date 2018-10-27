import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;


/**
 * 
 * MapData Test Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public class MapDataTest
{
    /**
     * Method tests mapData constructor
     * 
     */
    @Test
    public void testMapData()
    {
        final int year = 2018;
        final int month = 8;
        final int day = 30;
        final int hour = 17;
        final int minute = 45;
        // arbitrary values
        final String directory = "data";

        MapData mapData = new MapData(year, month, day, hour, minute, directory);
        String a = mapData.createFileName(year, month, day, hour, minute, directory);
        GregorianCalendar utcDatetime1 = new GregorianCalendar(year, month, day, hour, minute);
        utcDatetime1.setTimeZone(TimeZone.getTimeZone("UTC"));
        // creating objects and calendar
        Assert.assertEquals("Incorrect Filenmae Generated.", "data/201808301745.mdf", a);
        Assert.assertEquals("Incorrect Gregorian calender year generated", 2018, utcDatetime1.get(Calendar.YEAR));
        Assert.assertEquals("Incorrect Gregorian calender month generated", 8, utcDatetime1.get(Calendar.MONTH));
        Assert.assertEquals("Incorrect Gregorian calender day generated", 30, utcDatetime1.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals("Incorrect Gregorian calender hour generated", 17, utcDatetime1.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals("Incorrect Gregorian calender minute generated", 45, utcDatetime1.get(Calendar.MINUTE));
        // testing
    }

    /**
     * Method tests creatFileName of MapData
     * 
     * @throws IOException for parsing file
     */
    @Test
    public void testCreateFileName() throws IOException
    {
        final int year = 2018;
        final int month = 8;
        final int day = 30;
        final int hour = 17;
        final int minute = 45;
        final String directory = "data";
        String expected = "data/201808301745.mdf";
        // values for calling objects
        MapData mapData = new MapData(year, month, day, hour, minute, directory);
        mapData.parseFile();
        String a = mapData.createFileName(year, month, day, hour, minute, directory);
        // test
        Assert.assertEquals("Incorrect creatFilename() output", expected, a);

        final int year1 = 989;
        final int month1 = 10;
        final int day1 = 3;
        final int hour1 = 1;
        final int minute1 = 4;
        final String directory1 = "data";

        String expected1 = "data/098910030104.mdf";
        // second set of tests and values for testing
        MapData mapData1 = new MapData(year1, month1, day1, hour1, minute1, directory1);

        String a1 = mapData1.createFileName(year1, month1, day1, hour1, minute1, directory1);
        // test 2
        Assert.assertEquals("Incorrect creatFilename() output", expected1, a1);
    }

    /**
     * Method tests the getIndexOf in MapData
     * 
     * @throws IOException for parsing file
     */
    @Test
    public void testGetIndexOf() throws IOException
    {
        final int year = 2018;
        final int month = 8;
        final int day = 30;
        final int hour = 17;
        final int minute = 45;
        final String directory = "data";
        // values for calling objects
        MapData mapData = new MapData(year, month, day, hour, minute, directory);
        mapData.parseFile();
        // getting expected and actual
        Integer x = mapData.getIndexOf("STID");
        Integer y = new Integer(1);
        // testing
        Assert.assertEquals("Incorrect test get Index of.", y, x);
    }

    /**
     * Method tests ParseFile in MapData
     * 
     * @throws IOException for parsing file
     */
    @Test
    public void testParseFile() throws IOException
    {
        
        // expected output
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;
        final String directory = "data";
        // values for object
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
        mapData.parseFile();
        // activating mapData for correct data assignment
        
        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics a = new Statistics(36.5, "HOOK", c, 116, StatsType.MAXIMUM);
        Assert.assertEquals(a.toString(),mapData.getStatistics(StatsType.MAXIMUM, "TAIR").toString());
    }

    /**
     * Method tests getStatistics method in mapData
     * 
     * @throws IOException for parsing file
     */
    @Test
    public void testGetStatistics() throws IOException
    {
        final int year = 2018;
        final int month = 8;
        final int day = 30;
        final int hour = 17;
        final int minute = 45;
        final String directory = "data";
        // values for calling objects
        MapData mapData = new MapData(year, month, day, hour, minute, directory);
        mapData.parseFile();

        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics a = new Statistics(36.5, "HOOK", c, 116, StatsType.MAXIMUM);
        Assert.assertEquals(a.toString(),mapData.getStatistics(StatsType.MAXIMUM, "TAIR").toString());

    }

    /**
     * Method test to String method in mapData
     * 
     * @throws IOException for parsing file
     */
    @Test
    public void testToString() throws IOException
    {
        String expected = "========================================================\r\n"
                + "=== 2018-08-30 17:45:00 UTC ===\r\n" + "========================================================\r\n"
                + "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\r\n"
                + "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\r\n"
                + "Average Air Temperature[1.5m] = 32.4 C at MESONET\r\n"
                + "========================================================\r\n"
                + "========================================================\r\n"
                + "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\r\n"
                + "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\r\n"
                + "Average Air Temperature[9.0m] = 31.6 C at MESONET\r\n"
                + "========================================================\r\n"
                + "========================================================\r\n"
                + "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\r\n"
                + "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\r\n"
                + "Average Solar Radiation[1.5m] = 828.1 W/m^2 at MESONET\r\n"
                + "========================================================\r\n";
        // expected output
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;
        final String directory = "data";
        // values for object
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
        mapData.parseFile();
        // activating mapData for correct data assignment
        
        Assert.assertEquals("Incorrect toString() output.", expected, mapData.toString());
    }

}
