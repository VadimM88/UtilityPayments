package utilitypays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UtilityPaymentsAcceptanceApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(UtilityPaymentsAcceptanceApplication.class);
        //FillData.fillTestData(ctx);


    }

}
