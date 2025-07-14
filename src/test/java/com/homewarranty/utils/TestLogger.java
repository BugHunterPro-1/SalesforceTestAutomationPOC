package com.homewarranty.utils;

import com.aventstack.extentreports.Status;
import com.homewarranty.BaseTest;
import org.openqa.selenium.WebDriver;

public class TestLogger {
    public static void log(String message, WebDriver driver) {
        ExtentTestManager.getTest().log(Status.INFO, message);
        try {
            BaseTest baseTest = new BaseTest();
            String screenshotPath = baseTest.takeScreenshot(message, driver);
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}