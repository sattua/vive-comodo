package access;

import org.joda.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        LocalTime currentTime = new LocalTime();
        System.out.println("The app is running -> " + currentTime);
    }
}
