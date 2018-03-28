package hazardparking;

import java.io.File;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan Johnston
 */
@RestController
public class RouteController {

    private static final String template = "Hello, %s!";

    /*
     * This route is for testing any back end methods adjust it to suite needs
     * @param q this is an optional query parameter, if you need an input when sending
     *              the request from the browser append ?q=(your value) to the url
     * @return The output you would like to send back, change the method type to suite this.s
     */
    @RequestMapping("/test")
    public String Test(@RequestParam(value="q", defaultValue="") String q) throws Exception{
        return "index";
    }

    /**
     *
     * @return static data object representing the csv file
     */
    @RequestMapping("/data")
    public Entry[] tickets(){
        Entry[] data = ExtractData.getData();
        return data;
    }
    //example commented out below
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
    @RequestMapping("/sort")
    public boolean sort(){
        Entry[] data = ExtractData.getData();


        Sort.sort(data);

        return Sort.isSorted(data);
    }
    
}
