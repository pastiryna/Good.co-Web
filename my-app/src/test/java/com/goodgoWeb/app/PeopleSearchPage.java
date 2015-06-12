package com.goodgoWeb.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by iryna on 09.06.15.
 */
public class PeopleSearchPage {
    final WebDriver driver;
    String baseUrl = "";

    public PeopleSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //list of elements on the page

    @FindBy(css =".ng-pristine.ng-valid")
    WebElement searchProfileField;

    @FindBy (css = ".circle.icon.icon-facebook")
    WebElement goodCoOnFacebookPage;

    @FindBy (css = ".circle.icon.icon-social-twitter")
    WebElement goodCoOnTwitterPage;

    @FindBy (css = ".circle.icon.icon-angel-list")
    WebElement goodCoAngelListPage;

    @FindBy (css = ".circle.icon.icon-linkedin")
    WebElement goodCoLinkedinPage;



    public boolean searchForCompany(String companyName){

        searchProfileField.sendKeys(companyName);
        WebElement searchResult = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(companyName)));
        return driver.findElement(By.linkText(companyName)).isDisplayed();
    }

    public boolean searchNotExistingCompany(String abracadabra){
        searchProfileField.sendKeys(abracadabra);
        WebElement searchResult = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[2]/div[1]/div/div[1]/div/h6")));
        return driver.findElement(By.xpath("html/body/div[2]/div[1]/div/div[1]/div/h6")).isDisplayed();
    }


    public CompanyProfilePage openCompanyProfilePage(String companyName) {

        this.searchForCompany(companyName);
        driver.findElement(By.linkText(companyName)).click();
        WebElement companyProfile = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gc-btn-prpl.claim")));
        return new CompanyProfilePage();  //?
    }

    public String socialLinkPage(WebElement clickedLink){

        clickedLink.click();

        //working switching to a new tab!!!
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        //*******

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.getCurrentUrl();

    }

    public void open() {
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    public void close(){
        driver.quit();
    }
}
