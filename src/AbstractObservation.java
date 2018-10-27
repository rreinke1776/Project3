/**
 * 
 * AbstractObservation Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public abstract class AbstractObservation
{
    protected boolean valid;

//get to use variable in observation class
    /**
     * Abstract Observation Constructor helps with Observations
     * 
     * 
     */
    public AbstractObservation()
    {
        valid = false;
    }

    public abstract boolean isValid();
//abstract boolean is valid method

}
