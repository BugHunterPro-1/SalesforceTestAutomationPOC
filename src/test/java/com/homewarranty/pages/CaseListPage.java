package com.homewarranty.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CaseListPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@name='Case-search-input']")
    WebElement searchList;

    @FindBy(xpath = "//a[@title='Change Owner' and @role='button']//div[@title='Change Owner' and text()='Change Owner']")
    WebElement changeOwnerButton;

    @FindBy(xpath = "//input[@title='Search Users']")
    WebElement userSearchInput;

    @FindBy(xpath = "//div[@title='Himani Parmar']")
    WebElement himaniParmarOption;

    @FindBy(xpath = "//span[text()='Send notification email']")
    WebElement notificationCheckbox;

    @FindBy(xpath = "(//button[contains(., 'Submit')])[2]")
    WebElement submitButton;

    public CaseListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void searchForCase(String caseNumber) {
        wait.until(ExpectedConditions.visibilityOf(searchList)).sendKeys(caseNumber);
        searchList.sendKeys(Keys.ENTER);
    }

    public void changeOwner(String caseNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'" + caseNumber + "')]/ancestor::tr//label"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(changeOwnerButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(userSearchInput)).click();
        wait.until(ExpectedConditions.elementToBeClickable(himaniParmarOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(notificationCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getOwnerAlias(String caseNumber) {
        By ownerAliasLocator = By.xpath("(//a[@title='" + caseNumber + "']/ancestor::tr/td[6]//span)[2]");
        wait.until(ExpectedConditions.textToBe(ownerAliasLocator, "Himani P"));
        WebElement ownerAlias = driver.findElement(ownerAliasLocator);
        return ownerAlias.getText();
    }

    public void openCase(String caseNumber) {
        WebElement caseLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='" + caseNumber + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", caseLink);
    }
}