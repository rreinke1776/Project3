import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * Statistics Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-3 Lab 13
 *
 */
public class Statistics extends Observation implements DateTimeComparable
{
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    protected DateTimeFormatter format;
    private GregorianCalendar utcDateTime;
    private ZonedDateTime zdtDateTime;
    private int numberOfReportingStations;
    private StatsType statType;

    /**
     * Constructor for statistics that contains gregorianCalendar
     * 
     * @param value                 of statistics
     * @param stid                  of observation (name)
     * @param dateTime              of statistics
     * @param numberOfValidStations from data
     * @param inStatType            type of statistic that is desired
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.utcDateTime = dateTime;
        // pass in variable values
        createStringFromDate(this.utcDateTime);
        //System.out.println(numberOfReportingStations);
    }

    /**
     * Constructor for statistics that contains ZDateTime
     * 
     * @param value                 of statistic
     * @param stid                  name of place statistics is from
     * @param dateTime              of statistic desired
     * @param numberOfValidStations valid stations in data set
     * @param inStatType            type of statistic desired
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.statType = inStatType;
        this.numberOfReportingStations = numberOfValidStations;
        this.zdtDateTime = dateTime;
        // pass in variable values
        createStringFromDate(zdtDateTime);
        //System.out.println(numberOfReportingStations);
    }

    /**
     * Method creates a GregorianCalendar date from a given string.
     * 
     * @param dateTimeStr given string
     * @return gregorianCalendar from string
     */
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = null;
        try // might throw a pasrseExeption if dateTimeStr is in bad format
        {
            date = df.parse(dateTimeStr);
        } catch (ParseException e)
        {

            System.out.println("Incorrect string format for parsing to date.");
            e.printStackTrace();
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        // creating gregorian calendar after parsing
        return cal;

    }

    /**
     * Method creates a ZDate from a given string
     * 
     * @param dateTimeStr of given string
     * @return ZonedDateTime from string
     */
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        ZonedDateTime date = ZonedDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        // parsing the inputed string
        return date;

    }

    /**
     * Method creates a string from a GeogorianCalendar
     * 
     * @param calendar inputed calendar
     * @return String string formated according to DATE_TIME_FORMAT
     */
    public String createStringFromDate(GregorianCalendar calendar)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_TIME_FORMAT);
        fmt.setCalendar(calendar);
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = fmt.format(calendar.getTime());
        // parsing calendar to string

        return dateFormatted;

    }

    /**
     * Method creates a string from ZDate
     * 
     * @param calendar date to be changed to string
     * @return String to be output from ZDate
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        return (DateTimeFormatter.ofPattern(this.DATE_TIME_FORMAT).format(calendar));

    }

    /**
     * Method returns number of valid reporting stations
     * 
     * @return number of valid stations
     */
    public int getNumberOfReportingStations()
    {
        return this.numberOfReportingStations;

    }

    /**
     * Method returns a string of UTCDateTime
     * 
     * @return String of formatted date
     */
    public String getUTCDateTimeString()
    {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_TIME_FORMAT);
        fmt.setCalendar(this.utcDateTime);
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = fmt.format(utcDateTime.getTime());
        // parsing calender to string
        return dateFormatted;

    }

    /**
     * Method tests to see if inputed date is before statistic date
     * 
     * @return true or false depending if it is before date or not
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.before(inDateTime);

    }

    /**
     * Method returns true or false if date is before or not calendar
     * 
     * @return true or false depending on input date
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.after(inDateTime);
    }

    /**
     * Method returns true or false based on input date and statistic date
     * 
     * @return true or false depending on input date
     */
    public boolean SameAs(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.equals(inDateTime);
    }

    /**
     * Method returns true or false based on input date and statistic date
     * 
     * @return true or false depending on input date
     */
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return zdtDateTime.isBefore(inDateTime);

    }

    /**
     * Method returns true or false based on input date and statistic date
     * 
     * @return true or false depending on input date
     */
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        return zdtDateTime.isAfter(inDateTime);
    }

    /**
     * Method returns true or false based on input date and statistic date
     * 
     * @return true or false depending on input date
     */
    public boolean SameAs(ZonedDateTime inDateTime)
    {
        return zdtDateTime.equals(inDateTime);
    }

    /**
     * Method outputs a string of statistics
     * 
     * @return String of formatted statistics data
     */
    public String toString()// TODO Not sure what to do with this.
    {
        String ran = createStringFromDate(utcDateTime);
        String a = String.format("%s %s %.1f %s", statType, getStid(), getValue(), ran);
        // formatting string
        return a;
    }

}
