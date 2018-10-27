import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * 
 * MapData Class for Project
 * 
 * @author Reece Reinke
 * @version 2018-10-25 Lab 13
 *
 */
public class MapData
{
    private HashMap<String, ArrayList<Observation>> dataCatalog;
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;
    private TreeMap<String, Integer> parapmPositions;
    // final private int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    private Integer numberOfStations = null;
    final private String TA9M = "TA9M";
    final private String TAIR = "TAIR";
    final private String SRAD = "SRAD";
    final private String STID = "STID";
    final private String MESONET = "MESONET";
    private String fileName;
    private GregorianCalendar utcDateTime;

    TreeMap<String, Statistics> statsTotal = new TreeMap<String, Statistics>();
    TreeMap<String, Statistics> statsMaximum = new TreeMap<String, Statistics>();
    TreeMap<String, Statistics> statsMinimum = new TreeMap<String, Statistics>();
    TreeMap<String, Statistics> statsAverage = new TreeMap<String, Statistics>();

    /**
     * Constructor for MapData
     * 
     * 
     * @param year      year of Data taken
     * @param month     month of data taken
     * @param day       day of data taken
     * @param hour      hour of data taken
     * @param minute    minute of data taken
     * @param directory directory that data will be found in
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        createFileName(year, month, day, hour, minute, directory);

        this.utcDateTime = new GregorianCalendar(year, month - 1, day, hour, minute);
        utcDateTime.setTimeZone(TimeZone.getTimeZone("UTC"));
        prepareDataCatalog();
    }

    /**
     * Method that creates the filename of the data based of inputed data time
     * 
     * @param year      of data taken
     * @param month     of data taken
     * @param day       of data taken
     * @param hour      of data taken
     * @param minute    of data taken
     * @param directory folder that data is found in
     * @return String that is the file name of the data
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        String year1;
        String month1;
        String day1;
        String hour1;
        String minute1;
        // Declaring strings to format

        if (year < 999)
        // Filtering to pad with 0's
        {
            year1 = String.format("%04d", year);
        } else
        {
            year1 = String.format("%d", year);
        }
        if (month < 10)
        // Filtering to pad with 0's
        {
            month1 = String.format("%02d", month);
        } else
        {
            month1 = String.format("%d", month);
        }
        if (day < 10)
        // Filtering to pad with 0's
        {
            day1 = String.format("%02d", day);
        } else
        {
            day1 = String.format("%d", day);
        }
        if (hour < 10)
        // Filtering to pad with 0's
        {
            hour1 = String.format("%02d", hour);
        } else
        {
            hour1 = String.format("%d", hour);
        }
        if (minute < 10)
        // Filtering to pad with 0's
        {
            minute1 = String.format("%02d", minute);
        } else
        {
            minute1 = String.format("%d", minute);
        }

        this.fileName = String.format("%s/%s%s%s%s%s.mdf", directory, year1, month1, day1, hour1, minute1);
        // final formatted file name
        return fileName;
    }

    /**
     * Method that parses the header and finds the location of the columns of
     * STID,TAIR,SRAD and TA9M
     * 
     * @param inParaStr header to be parsed
     */
    private void parseParamHeader(String inParaStr)
    {
        String[] temp = (inParaStr.split("\\s+"));

        for (int i = 0; i < temp.length; ++i)
        {
            if (temp[i].equalsIgnoreCase(STID))
            {
                this.parapmPositions.put(STID, i);
            }
            if (temp[i].equalsIgnoreCase(SRAD))
            {
                this.parapmPositions.put(SRAD, i);
            }
            if (temp[i].equalsIgnoreCase(TA9M))
            {
                this.parapmPositions.put(TA9M, i);
            }
            if (temp[i].equalsIgnoreCase(TAIR))
            {
                this.parapmPositions.put(TAIR, i);
            }
        }
    }

    /**
     * Method that gets the index of STID,TAIR,STID,TA9M
     * 
     * @param ParamStr what to get the index of
     * @return Integer of string index
     */
    public Integer getIndexOf(String ParamStr)
    {
        return parapmPositions.get(ParamStr);
    }

    /**
     * Method that parses throw the file and assigning data to arraylist and then to
     * maps.
     * 
     * 
     * @throws IOException in-case we have an I/O error
     */
    public void parseFile() throws IOException
    {
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();
        // declaring arraylists for assigning values to

        Double value0;
        Double value1;
        Double value2;

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String strg;
        // reading through unwanted file data before assigning values
        strg = br.readLine();
        strg = br.readLine();
        strg = br.readLine();
        parseParamHeader(strg);
        strg = br.readLine();

        @SuppressWarnings("unused")
        int i = 0; // its actually used tho...
        // while loop with not null parameter for looping through files
        while (strg != null)
        {

            String[] temp = (strg.split("\\s+"));
            // Splitting string by spaces
            value0 = Double.parseDouble(temp[getIndexOf(TAIR)]);
            tairData.add(new Observation(value0, temp[getIndexOf(STID)]));
            // assigning values to array list and sending them off
            value1 = Double.parseDouble(temp[getIndexOf(SRAD)]);
            sradData.add(new Observation(value1, temp[getIndexOf(STID)]));
            // assigning values to array list and sending them off
            value2 = Double.parseDouble(temp[getIndexOf(TA9M)]);
            ta9mData.add(new Observation(value2, temp[getIndexOf(STID)]));
            // assigning values to array list and sending them off

            // assigning values to array list and sending them off

            ++i;
            strg = br.readLine();

        }
        br.close();

        this.numberOfStations = new Integer(tairData.size());

        // assigning parsed values to data catalog
        this.dataCatalog.put(TAIR, tairData);
        this.dataCatalog.put(SRAD, sradData);
        this.dataCatalog.put(TA9M, ta9mData);
        // calling calculate statistics
        CalculateStatistics();

    }

    /**
     * Method that calculates all the statistics for desired measurement.
     * 
     * @param x hash map dataCatalog
     * @param y String of desired measurement
     */
    private void calculateAllStatistics(HashMap<String, ArrayList<Observation>> x, String y)
    {
        int missedObservations = 0;
        double minValue = 1000;
        double maxValue = -1000.0;
        double total = 0;
        int minIndex = 0;
        int maxIndex = 0;
        double Average;
        // assigning base values for calculations
        for (int i = 0; i < x.get(y).size(); ++i)
        // looping through arraylist
        {
            if (Boolean.TRUE.equals(x.get(y).get(i).isValid()))
            // more filtering
            {
                // max and min assigning
                if (minValue > x.get(y).get(i).getValue())
                {
                    minValue = x.get(y).get(i).getValue();
                    minIndex = i;

                }
                if (maxValue < x.get(y).get(i).getValue())
                {
                    maxValue = x.get(y).get(i).getValue();
                    maxIndex = i;
                }
                total += x.get(y).get(i).getValue();
                // total of all data taken
            }

            else
            {
                ++missedObservations;
                // observations that are bad
            }
        }
        // calculating and assigning tair Statitics objects values
        Average = total / (x.get(y).size() - missedObservations);
        this.statsMaximum.put(y, new Statistics(maxValue, x.get(y).get(maxIndex).getStid(), utcDateTime,
                numberOfStations - missedObservations, StatsType.MAXIMUM));
        this.statsMinimum.put(y, new Statistics(minValue, x.get(y).get(minIndex).getStid(), utcDateTime,
                numberOfStations - missedObservations, StatsType.MINIMUM));
        this.statsTotal.put(y,
                new Statistics(total, MESONET, utcDateTime, numberOfStations - missedObservations, StatsType.TOTAL));
        this.statsAverage.put(y, new Statistics(Average, MESONET, utcDateTime, numberOfStations - missedObservations,
                StatsType.AVERAGE));
        // System.out.println(statsAverage);
        // TODO How do I implement the zoned date Time?

    }

    /**
     * Method initialized all the maps to be used in mapData
     * 
     * 
     */
    private void prepareDataCatalog()
    {
        // initializing all maps
        this.dataCatalog = new HashMap<String, ArrayList<Observation>>();
        this.statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);
        this.parapmPositions = new TreeMap<String, Integer>();

    }

    /**
     * Method Calculates statistics by calling calculate all statistics
     * 
     * 
     */
    private void CalculateStatistics()
    {
        // calculating all statistics for specific measurement
        calculateAllStatistics(dataCatalog, TAIR);
        calculateAllStatistics(dataCatalog, SRAD);
        calculateAllStatistics(dataCatalog, TA9M);

        // assigning calculated statistics to enumMap that contains statistics
        statistics.put(StatsType.TOTAL, statsTotal);
        statistics.put(StatsType.MAXIMUM, statsMaximum);
        statistics.put(StatsType.MINIMUM, statsMinimum);
        statistics.put(StatsType.AVERAGE, statsAverage);

    }

    /**
     * Method gets desired statistics based on measurement and type of statistic
     * 
     * @param paramId String
     * @param type StatsType
     * @return Statistics of desired type
     */
    public Statistics getStatistics(StatsType type, String paramId)
    {
        return this.statistics.get(type).get(paramId);
    }

    /**
     * Method that formats mapData for output
     * 
     * @return String final output string
     */
    public String toString()
    {
        // coded this way to help visualize the output of the toString
        String Divider = "========================================================";
        String smallDivider = "===";

        String line1 = Divider;
        String line2 = String.format("%s %s %s", smallDivider,
                statistics.get(StatsType.AVERAGE).get(TAIR).createStringFromDate(this.utcDateTime), smallDivider);
        String line3 = Divider;
        String line4 = String.format("Maximum Air Temperature[1.5m] = %.1f C at %s",
                getStatistics(StatsType.MAXIMUM, TAIR).getValue(), getStatistics(StatsType.MAXIMUM, TAIR).getStid());
        String line5 = String.format("Minimum Air Temperature[1.5m] = %.1f C at %s",
                getStatistics(StatsType.MINIMUM, TAIR).getValue(), getStatistics(StatsType.MINIMUM, TAIR).getStid());
        String line6 = String.format("Average Air Temperature[1.5m] = %.1f C at %s",
                getStatistics(StatsType.AVERAGE, TAIR).getValue(), getStatistics(StatsType.AVERAGE, TAIR).getStid());
        String line7 = Divider;
        String line8 = Divider;
        String line9 = String.format("Maximum Air Temperature[9.0m] = %.1f C at %s",
                getStatistics(StatsType.MAXIMUM, TA9M).getValue(), getStatistics(StatsType.MAXIMUM, TA9M).getStid());
        String line10 = String.format("Minimum Air Temperature[9.0m] = %.1f C at %s",
                getStatistics(StatsType.MINIMUM, TA9M).getValue(), getStatistics(StatsType.MINIMUM, TA9M).getStid());
        String line11 = String.format("Average Air Temperature[9.0m] = %.1f C at %s",
                getStatistics(StatsType.AVERAGE, TA9M).getValue(), getStatistics(StatsType.AVERAGE, TA9M).getStid());
        String line12 = Divider;
        String line13 = Divider;
        String line14 = String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s",
                getStatistics(StatsType.MAXIMUM, SRAD).getValue(), getStatistics(StatsType.MAXIMUM, SRAD).getStid());
        String line15 = String.format("Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s",
                getStatistics(StatsType.MINIMUM, SRAD).getValue(), getStatistics(StatsType.MINIMUM, SRAD).getStid());
        String line16 = String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s",
                getStatistics(StatsType.AVERAGE, SRAD).getValue(), getStatistics(StatsType.AVERAGE, SRAD).getStid());
        String line17 = Divider;
        String complete = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", line1,
                line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15,
                line16, line17);
        return complete;
    }
}
