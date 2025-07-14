package com.homewarranty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='slds-icon-waffle']")
    WebElement appLauncher;

    @FindBy(xpath = "//input[contains(@placeholder,'Search apps and items...')]")
    WebElement searchInput;

    @FindBy(xpath = "//p/b[text()='Contacts']")
    WebElement contactsLink;

    @FindBy(xpath = "//p/b[text()='Cases']")
    WebElement casesLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToContacts() {
        wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys("Contacts");
        wait.until(ExpectedConditions.elementToBeClickable(contactsLink)).click();
    }

    public void navigateToCases() {
        wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys("Cases");
        wait.until(ExpectedConditions.elementToBeClickable(casesLink)).click();
    }
}