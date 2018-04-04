package hazardparking;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Steven Luu, Rico Florentino & Ethan Johnston
 */
public class ExtractData  {
	private static Entry[] data;
	private static String[] violationReasons;
	private static String[] violationCodes;
	private static HashMap<String, String> Violations; //for conversion of reasons to codes
	public static void init() throws Exception{
        data = extract(); //read data from csv
        Violations = new HashMap<>();
        //set exported variables
        violationReasons = getUniqueReasons();
        violationCodes = getUniqueCodes();
        setViolationData();
    }
    private static Entry[] extract() throws Exception{
        //Define buffered reader for reading file
        File file = new ClassPathResource("data/parking.csv").getFile();
        BufferedReader br = new BufferedReader(new FileReader(file));

        br.readLine();

        Entry[] entries = new Entry[132850];

        String entryinfo = br.readLine();
        LocalDateTime tempdate;

        int i = 0;
        while (entryinfo != null) {
            String[] splitinfo = entryinfo.split(",");

            if (splitinfo[8].length() == 0) {
                tempdate = LocalDateTime.of(Integer.parseInt(splitinfo[18].substring(0, 4)),Integer.parseInt(splitinfo[18].substring(5, 7)), Integer.parseInt(splitinfo[18].substring(8, 10)),00,00);
            } else if (splitinfo[8].length() == 4) {
                tempdate = LocalDateTime.of(Integer.parseInt(splitinfo[18].substring(0, 4)),Integer.parseInt(splitinfo[18].substring(5, 7)), Integer.parseInt(splitinfo[18].substring(8, 10)), Integer.parseInt(splitinfo[8].substring(0, 2)),Integer.parseInt(splitinfo[8].substring(2)));
            } else if (splitinfo[8].length() == 3) {
                tempdate = LocalDateTime.of(Integer.parseInt(splitinfo[18].substring(0, 4)),Integer.parseInt(splitinfo[18].substring(5, 7)), Integer.parseInt(splitinfo[18].substring(8, 10)), Integer.parseInt(splitinfo[8].substring(0, 1)),Integer.parseInt(splitinfo[8].substring(1)));
            } else {
                tempdate = LocalDateTime.of(Integer.parseInt(splitinfo[18].substring(0, 4)),Integer.parseInt(splitinfo[18].substring(5, 7)), Integer.parseInt(splitinfo[18].substring(8, 10)),00,Integer.parseInt(splitinfo[8]));
            }
            entries[i]= new Entry(Float.parseFloat((splitinfo[0])),Float.parseFloat(splitinfo[1]),splitinfo[4],tempdate,splitinfo[9],splitinfo[10],splitinfo[11]);
            i++;
            entryinfo = br.readLine();

        }
        return entries;
    }
    //GETTERS AND SETTERS
    public static Entry[] getData() {
        return data;
    }
    public static Entry getIndex(int index){
	    return data[index];
    }
    public static void setData(Entry[] newData) {
        data = newData;
    }
    public static String[] getViolationReasons() {
        return violationReasons;
    }
    public static String[] getViolationCodes() {
        return violationCodes;
    }
    public static void setViolationReasons(String[] violationReasons) {
        ExtractData.violationReasons = violationReasons;
    }
    public static void setViolationCodes(String[] violationCodes) {
        ExtractData.violationCodes = violationCodes;
    }

    /**
     * Set the hashmap for converting between violation reasons and codes easily
     */
    private static void setViolationData(){
        for(int i = 0; i < violationReasons.length;i++){
            if(Violations.get(violationReasons[i]) == null){
                Violations.put(violationReasons[i],violationCodes[i]);
            }
        }
    }

    /**
     * Convert violation reason to a corresponding violation code
     * @param reason the reason being converted
     * @return the violation code for this reason
     */
    public static String convertReason(String reason){
        return Violations.get(reason);
    }

    /**
     * used on init to create array of reasons which is used for searching and suggesting items on frontend
     * @return array of the unique violation reasons
     */
    private static String[] getUniqueReasons(){
        ArrayList<String> out = new ArrayList<>();
        for(Entry i : data){
            if(!out.contains(i.getViolationReason()))
                out.add(i.getViolationReason());
        }
        return out.stream().toArray(String[]::new);
    }
    /**
     * used on init to create array of codes which is used for searching and suggesting items on frontend
     * @return array of the unique violation codes
     */
    private static String[] getUniqueCodes(){
        ArrayList<String> out = new ArrayList<>();
        for(Entry i : data){
            if(!out.contains(i.getViolationCode()))
                out.add(i.getViolationCode());
        }
        return out.stream().toArray(String[]::new);
    }

    /**
     * Create an array of heatpoint items
     * @param entries array of entries to be converted
     * @return array of heatpoints of the form [double longitude,double latitude, double intensity]
     */
    public static double[][] convertEntriesToHeat(Entry[] entries){
        double[][] out = new double[entries.length][3];
        for (int i = 0; i < entries.length; i++){
            Point point = new Point(entries[i].getY(),entries[i].getX(), 0.015);
            out[i] = point.getHeatPoint();
        }
        return out;
    }
}
