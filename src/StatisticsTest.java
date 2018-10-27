import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Driver Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public class StatisticsTest
{
    /**
     * Method tests to String method of statistics
     * 
     */
    @Test
    public void testToString()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = null;

        try
        {
            date = df.parse("2018-08-30 17:45:00 UTC");
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        Statistics a = new Statistics(24.2, "MIAM", (GregorianCalendar) cal, 5, StatsType.MAXIMUM);
        // creating stat object
        Assert.assertEquals("Inccorect toString(0 method.", a.toString(),
                String.format("%s %s %.1f %s", StatsType.MAXIMUM, "MIAM", 24.2, "2018-08-30 17:45:00 UTC"));
        // test
    }

    /**
     * Method tests constructor of Statistics
     * 
     */
    @Test
    public void testStatisticsDoubleStringGregorianCalendarIntStatsType()
    {
        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        // stats object
        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.MAXIMUM);

        double b = a.getValue();
        String stid = a.getStid();
        String date = a.getUTCDateTimeString();
        int stations = a.getNumberOfReportingStations();
        // getting values for tests
        Assert.assertEquals("Incorrect Statistics method.", 24.2, b, 0.1);
        Assert.assertEquals("Incorrect Statistics method.", stid, "MIAM");
        Assert.assertEquals("Incorrect Statistics method.", date, "2018-08-30 17:45:00 UTC");
        Assert.assertEquals("Incorrect Statistics method.", stations, 5);
    }

    /**
     * Method test Statistics constructor
     * 
     */
    @Test
    public void testStatisticsDoubleStringZonedDateTimeIntStatsType()
    {
        ZonedDateTime date = ZonedDateTime.parse("2018-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        Statistics a = new Statistics(24.2, "MIAM", date, 5, StatsType.MAXIMUM);

        double b = a.getValue();
        String stid = a.getStid();
        String date1 = a.createStringFromDate(date);
        int stations = a.getNumberOfReportingStations();
        // getting values for tests
        Assert.assertEquals("Incorrect Statistics method.", 24.2, b, 0.1);
        Assert.assertEquals("Incorrect Statistics method.", stid, "MIAM");
        Assert.assertEquals("Incorrect Statistics method.", date1, "2018-08-30 17:45:00 UTC");
        Assert.assertEquals("Incorrect Statistics method.", stations, 5);
    }

    /**
     * Method tests CreateDateFromString of GregorianCalendar in Statistics
     * 
     */
    @Test
    public void testCreateDateFromString()
    {
        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Declaring a calendar of Gregorian type
        GregorianCalendar d = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        d.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Declaring a calendar of Gregorian type

        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.MAXIMUM);
        // making statistics object
        Assert.assertEquals("Incorrect String", c, a.createDateFromString("2018-08-30 17:45:00 UTC"));
        // trying to pass an incorrect string into method
        try
        {
            a.createDateFromString("SAHDKJASH");
            Assert.fail();
        } catch (Exception e)
        {

            Assert.assertEquals("Should throw exeption", 1, 1);
            // parse test for fail
        }

    }

    /**
     * Method tests CreateZDateFromString in statistics
     * 
     */
    @Test
    public void testCreateZDateFromString()
    {
        ZonedDateTime date = ZonedDateTime.parse("2018-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        Statistics a = new Statistics(24.2, "MIAM", date, 5, StatsType.MAXIMUM);

        Assert.assertEquals("Incorrect ZDateToString", date, a.createZDateFromString("2018-08-30 17:45:00 UTC"));
    }

    /**
     * Method test CreateStringFromDateGregorianCalendar in statistics
     * 
     */
    @Test
    public void testCreateStringFromDateGregorianCalendar()
    {
        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        // stats object
        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.MAXIMUM);
        // test
        Assert.assertEquals("INcorrect String from greg date.", "2018-08-30 17:45:00 UTC", a.createStringFromDate(c));
    }

    /**
     * Method tests GetUTCDateString in Statistics
     * 
     */
    @Test
    public void testGetUTCDateTimeString()
    {
        // creating GregorianCalendar
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = null;

        try
        {
            date = df.parse("2018-08-30 17:45:00 UTC");
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        Statistics a = new Statistics(24.2, "MIAM", (GregorianCalendar) cal, 5, StatsType.MAXIMUM);
        // creating statistics object
        Assert.assertEquals("Incorrect utcDateTimeString.", "2018-08-30 17:45:00 UTC", a.getUTCDateTimeString());
        // test
    }

    /**
     * Method test Newer thanGregorianCalendar
     * 
     */
    @Test
    public void testNewerThanGregorianCalendar()
    {
        GregorianCalendar c = new GregorianCalendar(2018, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.MAXIMUM);
        // setting up calendat for use
        Boolean expected = false;

        Assert.assertEquals("Incorrect Newer than", expected, a.newerThan(c));
        // test
        GregorianCalendar d = new GregorianCalendar(2015, 8 - 1, 30, 17, 45);
        d.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics b = new Statistics(24.2, "MIAM", d, 5, StatsType.MAXIMUM);
        // setting up calendar
        expected = true;

        Assert.assertEquals("Incorrect Newer than", expected, b.newerThan(c));
        // test
    }

    /**
     * Method tests OlderThanGregorianCalendar
     * 
     */
    @Test
    public void testOlderThanGregorianCalendar()
    {
        GregorianCalendar c = new GregorianCalendar(2019, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.MINIMUM);
        // setting up calendar for use
        Boolean expected = false;

        Assert.assertEquals("Incorrect Newer than", expected, a.olderThan(c));
        // test
        GregorianCalendar d = new GregorianCalendar(2015, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics b = new Statistics(24.2, "MIAM", c, 5, StatsType.MINIMUM);
        // settting up calendar for use
        expected = true;

        Assert.assertEquals("Incorrect Newer than", expected, b.olderThan(d));
        // test
    }

    /**
     * Method tests SameAsGregorianCalendar
     * 
     */
    @Test
    public void testSameAsGregorianCalendar()
    {
        GregorianCalendar c = new GregorianCalendar(2019, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics a = new Statistics(24.2, "MIAM", c, 5, StatsType.TOTAL);
        // setting up calendar for use
        Boolean expected = true;

        Assert.assertEquals("Incorrect Newer than", expected, a.SameAs(c));
        // test
        GregorianCalendar d = new GregorianCalendar(2015, 8 - 1, 30, 17, 45);
        c.setTimeZone(TimeZone.getTimeZone("UTC"));
        Statistics b = new Statistics(24.2, "MIAM", c, 5, StatsType.TOTAL);
        // setting up calendar for use
        expected = false;

        Assert.assertEquals("Incorrect Newer than", expected, b.SameAs(d));
        // test
    }

    /**
     * Method Tests NewerThanZonedDateTime in statistics
     * 
     */
    @Test
    public void testNewerThanZonedDateTime()
    {
        ZonedDateTime date = ZonedDateTime.parse("2018-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating Zdate objects
        ZonedDateTime date1 = ZonedDateTime.parse("2015-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating zDate Time objecst

        Statistics a = new Statistics(24.2, "MIAM", date1, 5, StatsType.MAXIMUM);
        // creating statistics object
        Boolean expected = true;

        Assert.assertEquals("Incorrect OLder than", expected, a.newerThan(date));
        // tests
        Statistics b = new Statistics(24.2, "MIAM", date, 5, StatsType.MAXIMUM);
        // creating statistics object
        expected = false;
        // changing expected
        Assert.assertEquals("Incorrect OLder than", expected, b.newerThan(date1));
        // test
    }

    /**
     * Method Tests OlderThanDateTime in Statistics
     * 
     */
    @Test
    public void testOlderThanZonedDateTime()
    {
        ZonedDateTime date = ZonedDateTime.parse("2018-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating zDate Time object
        ZonedDateTime date1 = ZonedDateTime.parse("2015-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating zDate Time object

        Statistics a = new Statistics(24.2, "MIAM", date, 5, StatsType.MAXIMUM);
        // creating statistics object
        Boolean expected = true;

        Assert.assertEquals("Incorrect OLder than", expected, a.olderThan(date1));
        Statistics b = new Statistics(24.2, "MIAM", date1, 5, StatsType.MAXIMUM);
        // creating statistics object
        expected = false;
        Assert.assertEquals("Incorrect OLder than", expected, b.olderThan(date));
        // test

    }

    /**
     * Method tests SameAsZonedDateTime in statistics
     * 
     * 
     */
    @Test
    public void testSameAsZonedDateTime()
    {
        ZonedDateTime date = ZonedDateTime.parse("2018-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating zDate Time object
        ZonedDateTime date1 = ZonedDateTime.parse("2015-08-30 17:45:00 UTC",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        // creating zDate Time object

        Statistics a = new Statistics(24.2, "MIAM", date1, 5, StatsType.MAXIMUM);
        // creating statistics object
        Boolean expected = true;

        Assert.assertEquals("Incorrect same as", expected, a.SameAs(date1));
        // test
        Statistics b = new Statistics(24.2, "MIAM", date, 5, StatsType.MAXIMUM);
        // creating statistics object
        expected = false;
        Assert.assertEquals("Incorrect same as", expected, b.SameAs(date1));
        // test
    }

}
