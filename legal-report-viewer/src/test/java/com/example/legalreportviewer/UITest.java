package com.example.legalreportviewer;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.testng.Assert;
import org.testng.annotations.Test;

//@SpringBootTest()
public class UITest extends BaseTest {

    @Test
    public void testPhysicalPersonUI(){

        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("authorization")).click();

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

        WebElement topic = driver.findElement(By.xpath("//p[contains(text(),'Your balance is ')]"));
        Assert.assertTrue(topic.isDisplayed());
    }

    @Test
    public void legalPersonUISelenideTest(){
        open("/");
        $(By.linkText("authorization")).click();
        $(By.name("login")).setValue("gaz");
        $(By.name("password")).setValue("Uassia1!");
        $(By.name("password")).pressEnter();
        $(By.linkText("create bill to physical person")).click();
        $(By.name("yearp")).setValue("2022");
        $(By.name("monthp")).setValue("7");
        $(By.name("quantity")).setValue("7");
        $(By.name("passpnum")).setValue("6002 223344").pressEnter();
        $(By.name("submit")).click();
        $(By.name("message")).shouldHave(text("Bill on 48 rub saved successfully!"));
    }
}

