package com.homewarranty.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(tagName = "body")
    WebElement body;

    @FindBy(xpath = "//a[@title='John Smith']")
    WebElement johnSmithLink;

    @FindBy(xpath = "//button[@name='NewCase' and text()='New']")
    WebElement newCaseButton;

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void setZoom() {
        Actions actions = new Actions(driver);
        actions.sendKeys(body, Keys.chord(Keys.CONTROL, Keys.SUBTRACT)).perform();
        actions.sendKeys(body, Keys.chord(Keys.CONTROL, Keys.SUBTRACT)).perform();
    }

    public void navigateToJohnSmith() {
        wait.until(ExpectedConditions.elementToBeClickable(johnSmithLink)).click();
    }

    public void clickNewCase() {
        WebElement newCaseBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='NewCase' and text()='New']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newCaseBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newCaseBtn);
    }
}