import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * 
 * Public Interface class dateTimecomparable for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public interface DateTimeComparable
{
    /**
     * NewerThan Class interfaces with statistics
     * 
     * @param inDateTimeUTC Calendar
     * @return true or false
     */
    public boolean newerThan(GregorianCalendar inDateTimeUTC);

    /**
     * OlderThan Class interfaces with statistics
     * 
     * @param inDateTimeUTC calendar
     * @return true or false
     */
    public boolean olderThan(GregorianCalendar inDateTimeUTC);

    /**
     * SameAs Class interfaces with statistics
     * 
     * @param inDateTimeUTC Calendar
     * @return true or false
     */
    public boolean SameAs(GregorianCalendar inDateTimeUTC);

    /**
     * NewerThan Class interfaces with statistics
     * 
     * @param inDateTimeUTC Calendar
     * @return true or false
     */
    public boolean newerThan(ZonedDateTime inDateTimeUTC);

    /**
     * OlderThan Class interfaces with statistics
     * 
     * @param inDateTimeUTC Calendar
     * @return true or false
     */
    public boolean olderThan(ZonedDateTime inDateTimeUTC);

    /**
     * SameAs Class interfaces with statistics
     * 
     * @param inDateTimeUTC Calendar
     * @return true or false
     */
    public boolean SameAs(ZonedDateTime inDateTimeUTC);
}
