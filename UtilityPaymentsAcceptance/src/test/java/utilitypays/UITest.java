package utilitypays;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import org.testng.annotations.Test;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class UITest extends BaseTest {

    @Test
    public void testPhysicalPersonUI() {

        driver.get("http://localhost:8080/");

        ((ChromeDriver) driver).findElementByPartialLinkText("authorization").click();

        WebElement searchLogin = driver.findElement(By.xpath("//input[@name='login']"));
        searchLogin.sendKeys("VadimM88");
        WebElement searchPassword = driver.findElement(By.xpath("//input[@name='password']"));
        searchPassword.sendKeys("Uassia1!");
        searchPassword.sendKeys(Keys.ENTER);

        WebElement searchInn = driver.findElement(By.xpath("//input[@name='inn']"));
        searchInn.sendKeys("9999999999");
        searchInn.sendKeys(Keys.ENTER);

        WebElement inputSumpay = driver.findElement(By.xpath("//input[@name='sumpay']"));
        inputSumpay.sendKeys("555");
        inputSumpay.sendKeys(Keys.ENTER);

        WebElement topic = driver.findElement(By.xpath("//p[contains(text(),'Your balance is 555')]"));
        Assert.assertTrue(topic.isDisplayed());

    }

}

