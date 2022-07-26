package utilitypays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import utilitypays.entity.*;
import utilitypays.service.*;
import java.util.Calendar;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);
        FillData.fillTestData(ctx);


    }

}
