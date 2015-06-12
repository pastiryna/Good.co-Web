package com.goodgoWeb.app;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest{

    WebDriver driver;
    String companyName = "techmagic";

    public static void main(String args[]) {

        //String ChromeDriver = "/home/iryna/soft/chromedriver";
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
    }


    @Test
    public void open() throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("http://dev.good.co:9900/#!/search/company");

        sleep(1000);
        driver.findElement(By.id("company-search")).sendKeys(companyName);

        sleep(1000);
        driver.findElement(By.xpath("//*[contains(text(),companyName)]")).click();
                //driver.switchTo().alert();
        //driver.getCurrentUrl();
        //System.out.println(driver.getCurrentUrl().toString());

    }



}
