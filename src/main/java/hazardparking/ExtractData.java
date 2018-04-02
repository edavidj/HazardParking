package hazardparking;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Steven Luu, Rico Florentino & Ethan Johnston
 */
public class ExtractData  {
	private static Entry[] data;
	private static String[] violationReasons;
	private static String[] violationCodes;
	public static void init() throws Exception{
        data = extract();
        violationReasons = getUniqueReasons();
        violationCodes = getUniqueCodes();
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
    public static void setViolationReasons(String[] violationReasons) {
        ExtractData.violationReasons = violationReasons;
    }
    public static String[] getViolationCodes() {
        return violationCodes;
    }
    public static void setViolationCodes(String[] violationCodes) {
        ExtractData.violationCodes = violationCodes;
    }

    private static String[] getUniqueReasons(){
        ArrayList<String> out = new ArrayList<>();
        for(Entry i : data){
            if(!out.contains(i.getViolationReason()))
                out.add(i.getViolationReason());
        }
        return out.stream().toArray(String[]::new);
    }
    private static String[] getUniqueCodes(){
        ArrayList<String> out = new ArrayList<>();
        for(Entry i : data){
            if(!out.contains(i.getViolationCode()))
                out.add(i.getViolationCode());
        }
        return out.stream().toArray(String[]::new);
    }
    public static double[][] convertEntriesToHeat(Entry[] entries){
        double[][] out = new double[entries.length][3];
        for (int i = 0; i < entries.length; i++){
            Point point = new Point(entries[i].getY(),entries[i].getX(), 0.015);
            out[i] = point.getHeatPoint();
        }
        return out;
    }
}
