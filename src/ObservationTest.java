import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Observation Test Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public class ObservationTest
{
    /**
     * Method tests is Valid of Observation
     * 
     */
    @Test
    public void testIsValid()
    {
        Observation a = new Observation(10.0, "MIAM");
        Assert.assertEquals("Incorrect IsValid method().", true, a.isValid());
        // test
        // making observation object (false)
        Observation b = new Observation(-999.0, "MIAM");
        Assert.assertEquals("Incorrect IsValid method().", false, b.isValid());
    }

    /**
     * Method tests Observation constructor
     * 
     */
    @Test
    public void testObservation()
    {
        // making observation object (true)
        Observation a = new Observation(10.0, "MIAM");
        double b = a.getValue();
        String c = a.getStid();
        // testing if correctly assigned in observation
        Assert.assertEquals("Incorrect Observation constructor.", b, 10.0, 0.1);
        Assert.assertEquals("Incorrect Observation constructor.", c, "MIAM");
    }

    /**
     * Method tests get Value in Observation
     * 
     */
    @Test
    public void testGetValue()
    {
        Observation a = new Observation(10.0, "MIAM");
        double b = a.getValue();
        // assigning objects
        Assert.assertEquals("Incorrect Observation constructor.", b, 10.0, 0.1);
        // testing
    }

    /**
     * Method tests getStid from Observation
     * 
     */
    @Test
    public void testGetStid()
    {
        Observation a = new Observation(10.0, "MIAM");
        String c = a.getStid();
        Assert.assertEquals("Incorrect Observation constructor.", c, "MIAM");

    }

    /**
     * Method test toString method of Observation
     * 
     */
    @Test
    public void testToString()
    {
        Observation a = new Observation(10.0, "MIAM");
        // making observation object (true)

        Assert.assertEquals("Incorrect to String() method.", a.toString(), String.format("%.1f at %s", 10.0, "MIAM"));
    }

}
