/**
 * @author Ethan Johnston
 * This class is for controlling the request routes on the web app server.
 */
package hazardparking;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    /*
     * This route is for testing any back end methods adjust it to suite needs
     * @param q this is an optional query parameter, if you need an input when sending
     *              the request from the browser append ?q=(your value) to the url
     * @return The output you would like to send back, change the method type to suite this.s
     */
    @RequestMapping("/test")
    public String[] Test(@RequestParam(value="q", defaultValue="") String q) throws Exception{
        return ExtractData.getViolationReasons();
    }

    /**
     * Query used for initial loading of the heatmap
     * @return array of heatpoints corresponding to the entire data set
     */
    @RequestMapping("/points")
    public double[][] points(){
        Entry[] data = ExtractData.getData();
        double[][] out = ExtractData.convertEntriesToHeat(data);
        return out;
    }
    /**
     * test for dataset/return entire dataset to front end
     * @return static data object representing the csv file
     */
    @RequestMapping("/data")
    public Entry[] data(){
        Entry[] data = ExtractData.getData();
        return data;
    }

    /**
     * test for sorting data
     * @return
     */
    @RequestMapping("/sort")
    public boolean sort(){
        Entry[] data = ExtractData.getData();
        Sort.sort(data, 4);
        return Sort.isSorted(data, 4);
    }
    
    @RequestMapping("/path")
    public String path() throws Exception{
        //your methods
        return FindPath.findPath();
    } 
    //========= FILTER ROUTES ===============
    // follow convention /filter/<filter-topic-here>
    /**
     * This route returns all points who's entries contain given violation code
     * @param code the violation code to filter by
     * @return array of points to put on the heatmap
     */
    @RequestMapping("/filter/violationCode")
    public double[][] filterByViolationCode(@RequestParam(value="q") String code){
        String[] a = ExtractData.getViolationCodes();
        if(!Arrays.asList(a).contains(code)){
            code = ExtractData.convertReason(code);
        }
        Entry[] data = ExtractData.getData();
        Entry[] filtered = Filter.violationCode(data, code);
        return  ExtractData.convertEntriesToHeat(filtered);
    }
    /**
     * Filter heatpoints to a specific day of the week
     * @param day the day of the week that is being filtered by
     * @return new heatpoints corresponding to the filtered data
     */
    @RequestMapping("/filter/weekDay")
    public double[][] filterByDay(@RequestParam(value="q") String day){
        Entry[] data = ExtractData.getData();
        Entry[] filtered = Filter.day(data, day);
        return ExtractData.convertEntriesToHeat(filtered);
    }

    /**
     * filter results to a specific date
     * @param date the date being filtered for
     * @return heat point items for the heatmap
     * @throws Exception
     */
    @RequestMapping("/filter/date")
    public double[][] filterByDate(@RequestParam(value="q") String date) throws Exception{
        Entry[] data = ExtractData.getData();
        //parse date object and get day of month, in the future would parse for day of the year.
        date = date + " 00:00"; //default time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime simpleDate = LocalDateTime.parse(date, formatter);
        int numDate = simpleDate.getDayOfMonth();
        //filter results and send back to the front end
        Entry[] filtered = Filter.date(data, numDate);
        return ExtractData.convertEntriesToHeat(filtered);
    }

    /**
     * Filter from start date to end date
     * @param from start of the date range
     * @param to end of the date range
     * @return heat points that fit inside this range of dates
     */
    @RequestMapping("/filter/dateRange")
    public double[][] filterByDateRange(@RequestParam(value="from") String from, @RequestParam(value="to") String to){
        Entry[] data = ExtractData.getData();
        int start = Integer.parseInt(from.split("/")[1]);
        int end = Integer.parseInt(to.split("/")[1]);
        Entry[] startFiltered = Filter.date(data,start);
        Entry[] endFiltered = Filter.date(data, end);
        ArrayList<Entry> x = new ArrayList<>(Arrays.asList(data));
        System.out.println();
        Entry[] filtered = Arrays.copyOfRange(data,x.indexOf(startFiltered[0]),x.indexOf(endFiltered[endFiltered.length - 1]) );
        return ExtractData.convertEntriesToHeat(filtered);
    }
    
    /*
    @RequestMapping("/graph")
    public String graph(){
        Entry[] data = ExtractData.getData();
        HashMap<Point, LinkedList<String>> count = new HashMap<>();
        for(Entry e : data) {
            Point p = new Point(e.getY(), e.getX(), 0.5);
            LinkedList<String> violations = count.get(p);
            if(!violations.contains(e.getViolationReason())){
                violations.add(e.getViolationReason());
                count.put(p, violations);
            }
        }


    }
    */
    //============ UI ROUTES AND METHODS  ==============
    /**
     * Gets the suggestions for the input text box and formats them for the front end frameworks dropdown tool
     * https://semantic-ui.com/modules/search.html#/usage "Server Responses - Category" for format example
     * @param q the string to suggest for
     * @return an anonymous object matching the format of the json object required by semantic ui search module
     */
    @RequestMapping("/categories")
    public Object categories(@RequestParam(value="q") String q){
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return new Object(){
            public final Object category1 = getSuggestions(q, ExtractData.getViolationReasons(), "Reasons");
            public final Object category2 = getSuggestions(q, ExtractData.getViolationCodes(), "Codes");
            public final Object category3 = getSuggestions(q, weekDays, "Days");
        };
    }
    /**
     * Local method for filtering out strings that do not contain the query string and formatting them for the json out     *
     * @param q passed query string from parent method
     * @param items list of items being search against
     * @param categoryName the name of the category to create
     * @return an anonymous object matching the format for semantic ui's search module
     */
    private Object getSuggestions(String q, String[] items, String categoryName){
        ArrayList<Object> out = new ArrayList<>(); //results of the filtering
        for(int i = 0; i < items.length; i++) {
            if(!items[i].toLowerCase().contains(q.toLowerCase())) //if q is not substring do not add to results
                continue;
            String item = items[i];
            out.add(new Object() {
                public final String description = categoryName.substring(0,categoryName.length()- 1);
                public final String title = item;
            });
        }
        return new Object(){
            public final ArrayList<Object> results = out;
            public final String name = categoryName;
        };
    }
}

