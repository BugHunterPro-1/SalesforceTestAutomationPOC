package com.homewarranty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@aria-label='Case Origin']")
    WebElement caseOriginDropdown;

    @FindBy(xpath = "//span[@title='Phone']")
    WebElement phoneOption;

    @FindBy(xpath = "//button[@aria-label='Case Reason']")
    WebElement caseReasonDropdown;

    @FindBy(xpath = "//span[@title='Breakdown']")
    WebElement breakdownOption;

    @FindBy(xpath = "//button[@aria-label='Trade']")
    WebElement tradeDropdown;

    @FindBy(xpath = "//span[@title='Appliance']")
    WebElement applianceOption;

    @FindBy(xpath = "//button[@aria-label='Item']")
    WebElement itemDropdown;

    @FindBy(xpath = "//span[@title='Refrigerator']")
    WebElement refrigeratorOption;

    @FindBy(xpath = "//button[@aria-label='Brand']")
    WebElement brandDropdown;

    @FindBy(xpath = "//span[@title='LG']")
    WebElement lgOption;

    @FindBy(xpath = "//input[@name='Model_Number__c']")
    WebElement modelNumberInput;

    @FindBy(xpath = "//input[@name='Serial_Number__c']")
    WebElement serialNumberInput;

    @FindBy(xpath = "//button[@aria-label='Problem Code']")
    WebElement problemCodeDropdown;

    @FindBy(xpath = "//span[@title='The unit is not working at all']")
    WebElement problemCodeOption;

    @FindBy(xpath = "//label[contains(., 'Problem Description')]/following-sibling::div//textarea")
    WebElement problemDescriptionInput;

    @FindBy(xpath = "//button[@aria-label='Severity']")
    WebElement severityDropdown;

    @FindBy(xpath = "//span[@title='Standard']")
    WebElement standardOption;

    @FindBy(xpath = "//button[@aria-label='Deductible Payment Option']")
    WebElement deductiblePaymentOptionDropdown;

    @FindBy(xpath = "//span[@title='Pay with card']")
    WebElement payWithCardOption;

    @FindBy(xpath = "//input[@name='Deductible_Amount__c' and @type='text']")
    WebElement deductibleAmountInput;

    @FindBy(xpath = "//span[text()='Assign using active assignment rule']")
    WebElement assignmentRuleCheckbox;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    WebElement saveButton;

    public CasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void createCase() {
        selectDropdownOption(caseOriginDropdown, phoneOption);
        selectDropdownOption(caseReasonDropdown, breakdownOption);
        selectDropdownOption(tradeDropdown, applianceOption);
        selectDropdownOption(itemDropdown, refrigeratorOption);
        selectDropdownOption(brandDropdown, lgOption);
        modelNumberInput.sendKeys("verify");
        serialNumberInput.sendKeys("verify");
        selectDropdownOption(problemCodeDropdown, problemCodeOption);
        problemDescriptionInput.sendKeys("the unit is not working at all");
        selectDropdownOption(severityDropdown, standardOption);
        selectDropdownOption(deductiblePaymentOptionDropdown, payWithCardOption);
        deductibleAmountInput.sendKeys("$100.00");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignmentRuleCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignmentRuleCheckbox);
        saveButton.click();
    }

    private void selectDropdownOption(WebElement dropdown, WebElement option) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public String getCaseNumber() {
        WebElement caseNumberHost = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[text()='Case Number']/ancestor::div[contains(@class,'slds-form-element')]//lightning-formatted-text)[2]")
        ));
        return caseNumberHost.getText();
    }
}