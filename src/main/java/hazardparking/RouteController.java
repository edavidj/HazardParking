package hazardparking;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan Johnston
 */
@RestController
public class RouteController {

    private static final String template = "Hello, %s!";
    @RequestMapping("/")
    public ArrayList<Entry> tickets(){
        ArrayList<Entry> data = ExtractData.getData();
        return data;
    }
    //example commented out below
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
}
