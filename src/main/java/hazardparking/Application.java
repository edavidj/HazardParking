package hazardparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    /**
     * Boot the web application on http://localhost:8080
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //init method must be called here or data will not be extracted.
        ExtractData.init();
        SpringApplication.run(Application.class, args);
    }
}
