/**
 * 
 * Observation Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public class Observation extends AbstractObservation
{
    private double value;
    private String stid;

    /**
     * Constructor for observation
     * 
     * @param value of data
     * @param stid  name of place
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
        // assiging values for use in other methods
    }

    /**
     * Method gets value of observation
     * 
     * @return value value of observation
     */
    public double getValue()
    {
        return value;
        // returning value
    }

    /**
     * Method returns if the observation is valid
     * 
     * @return true or false based on value's value
     */
    public boolean isValid()
    {
        return Math.abs(999.00 + value) > 0.00001;

    }

    /**
     * Method gets Stid value
     * 
     * @return stid name of observation
     */
    public String getStid()
    {
        return stid;
    }

    /**
     * Method formats observation for a string output
     * 
     * @return String format for output
     */
    public String toString()
    {
        return String.format("%.1f at %s", getValue(), getStid());

    }
}
