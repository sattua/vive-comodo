package main;

import org.joda.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LocalTime currentTime = new LocalTime();
        System.out.println("************************************************************");
        System.out.println("*** The app is running -> Port: 8080, time: " + currentTime + " ***");
        System.out.println("************************************************************");
    }
}
