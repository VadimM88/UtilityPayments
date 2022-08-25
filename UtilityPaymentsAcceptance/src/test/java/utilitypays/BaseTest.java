//package utilitypays;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.BeforeTest;
//
//public class BaseTest {
//    public WebDriver driver;
//
//    @BeforeTest
//    public void setUp () {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//    }
//    public void tearDown() {
//        driver.quit();
//    }
//}