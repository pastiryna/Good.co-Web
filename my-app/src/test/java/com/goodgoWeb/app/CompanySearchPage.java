package com.goodgoWeb.app;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


import static java.lang.Thread.sleep;


public class CompanySearchPage {

    WebDriver driver;
    String baseUrl = "http://staging-app.good.co/search/company";

    public CompanySearchPage(WebDriver driver){
       this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //list of elements on the page

    @FindBy(css ="#company-search")
    WebElement searchCompanyField;

    @FindBy (css = "html.no-js body.ng-scope div.ng-scope div.pure-menu.pure-menu-fixed.pure-menu-horizontal.ng-scope div.social-links.pure-u-sm-0 a div.circle.icon.icon-facebook")
    WebElement goodCoOnFacebookPage;

    @FindBy (css = ".circle.icon.icon-social-twitter")
    WebElement goodCoOnTwitterPage;

    @FindBy (css = ".circle.icon.icon-angel-list")
    WebElement goodCoAngelListPage;

    @FindBy (css = ".circle.icon.icon-linkedin")
    WebElement goodCoLinkedinPage;

    @FindBy (xpath ="/html/body/div[1]/div/div[1]/a[2]")
    WebElement downloadFromGoogle;


    @FindBy (xpath ="/html/body/div[1]/div/div[1]/a[1]")
    WebElement downloadFromAppStore;

    WebElement companyContainer;


    public boolean searchForCompany(String companyName) {
        searchCompanyField.clear();
        searchCompanyField.sendKeys(companyName);
        WebElement searchResult = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(companyName)));
        return driver.findElement(By.linkText(companyName)).isDisplayed();
    }



    public boolean searchNotExistingCompany(String abracadabra) throws InterruptedException {
        searchCompanyField.sendKeys(abracadabra);
        WebElement searchResult = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[2]/div[1]/div/div[1]/div/h6")));
                sleep(500);
                String b = searchResult.getText();

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
        String socialUrl;
        clickedLink.click();

        //working switching to a new tab and closing it!!!
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

// Perform the click operation that opens new window

// Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        socialUrl = driver.getCurrentUrl();

// Perform the actions on new window

// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);

        //*******
        return socialUrl;
// Continue with original browser (first window)

    }

    public void open() {
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
