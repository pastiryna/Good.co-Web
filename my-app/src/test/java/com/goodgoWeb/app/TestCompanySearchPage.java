package com.goodgoWeb.app;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;


public class TestCompanySearchPage{


    WebDriver driver = new FirefoxDriver();
    CompanySearchPage theCompany = new CompanySearchPage(driver);

    String searchedCompany = "Google";
    String companyUrl = "http://staging-app.good.co/company/google";
    String goodFacebook = "https://www.facebook.com/thegoodco";
    String goodTwitter = "https://twitter.com/ingoodco";
    String goodLinkedin = "https://www.linkedin.com/company/good.co";
    String goodAngel = "https://angel.co/good-co";
    String goodGoogle = "https://play.google.com/store/apps/details?id=co.good.android";
    String goodApple = "https://itunes.apple.com/us/app/good.co-match-your-personality/id892559034?utm_source=web&utm_medium=web&utm_campaign=App%20Store%20From%20Web";



    @Before
    public void openPage(){
        theCompany.open();
    }

    @Rule
    public TestScreenShotRule screenShotRule = new TestScreenShotRule();

    @Rule
    public TestName name = new TestName();


    @Test
    public void verifyOpenGoogleProfile() {
        theCompany.openCompanyProfilePage(searchedCompany);
        assertEquals(driver.getCurrentUrl(), companyUrl);
    }

    @Test
    public void verifyNotExistingSearch() throws InterruptedException {
        Assert.assertTrue(theCompany.searchNotExistingCompany("ggggg"));
        //theCompany.close();
    }

    @Test
    public void verifyFacebookLink() {
        assertEquals(theCompany.socialLinkPage(theCompany.goodCoOnFacebookPage),goodFacebook);

    }


    @Test
    public void verifyTwitterLink() {


    }

    @Test
    public void verifyLinkedinLink() {
        assertEquals(theCompany.socialLinkPage(theCompany.goodCoLinkedinPage), goodLinkedin);
       // theCompany.close();
    }

    @Test
    public void verifyAngelLink() {
        assertEquals(theCompany.socialLinkPage(theCompany.goodCoAngelListPage), goodAngel);
        //theCompany.close();
    }

    @Test
    public void verifyDownloadFromGoogleLink() {
        assertEquals(theCompany.socialLinkPage(theCompany.downloadFromGoogle), goodGoogle);
        //theCompany.close();
    }

    @Test
    public void verifyDownloadFromAppleLink() {
        assertEquals(theCompany.socialLinkPage(theCompany.downloadFromAppStore), goodApple);
        //theCompany.close();
    }


    @Test
    public void fail()
    {
        screenShotRule.setTestName(name.getMethodName());
        assertEquals(theCompany.socialLinkPage(theCompany.downloadFromAppStore), " ");
    }


   @After
    public void closePage(){
        driver.close();
    }


}
