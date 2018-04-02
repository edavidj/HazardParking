/**
 * @author Ethan Johnston
 * This class is for controlling the request routes on the web app server.
 */
package hazardparking;
import java.io.File;
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

    @RequestMapping("/points")
    public double[][] points(){
        Entry[] data = ExtractData.getData();
        double[][] out = ExtractData.convertEntriesToHeat(data);
        return out;
    }
    /**
     *
     * @return static data object representing the csv file
     */
    @RequestMapping("/data")
    public Entry[] data(){
        Entry[] data = ExtractData.getData();
        return data;
    }
    @RequestMapping("/sort")
    public boolean sort(){
        Entry[] data = ExtractData.getData();
        Sort.sort(data, 1);
        return Sort.isSorted(data, 1);
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
            code = ExtractData.getViolationReasons()[Arrays.asList(a).indexOf(code)];
            System.out.println(code);
        }
        Entry[] data = ExtractData.getData();
        Entry[] filtered = Filter.violationCode(data,code);
        return  ExtractData.convertEntriesToHeat(filtered);
    }
    //============ UI ROUTES AND METHODS  ==============
    /**
     * Gets the suggestions for the input text box and formats them for the front end frameworks dropdown tool
     * https://semantic-ui.com/modules/search.html#/usage "Server Responses - Category" for format example
     * @param q the string to suggest for
     * @return an anonymous object matching the format of the json object required by semantic ui search module
     */
    @RequestMapping("/categories")
    public Object categories(@RequestParam(value="q") String q){
        return new Object(){
            public final Object category1 = getSuggestions(q, ExtractData.getViolationReasons(), "Reasons");
            public final Object category2 = getSuggestions(q, ExtractData.getViolationCodes(), "Codes");
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
            //anonymous objects require variables to be final for some reason so had to create new final for condition
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

