package com.goodgoWeb.app;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;

import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;


public  class TestScreenShotRule extends TestWatcher {
    private String testName;
    static WebDriver driver;


        public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


    public static void takeScreenShot(String fileName) {

        File file = new File("ScreenShots", fileName + ".png");
        File tmpFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tmpFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        takeScreenShot(testName);
            }

    @Override
    protected void finished(Description description) {
        driver.quit();
    }
}


