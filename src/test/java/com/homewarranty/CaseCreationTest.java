package com.homewarranty;

import com.homewarranty.pages.*;
import com.homewarranty.utils.TestLogger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CaseCreationTest extends BaseTest {

    @Test
    public void testCaseCreation() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ContactsPage contactsPage = new ContactsPage(driver);
        CasePage casePage = new CasePage(driver);
        CaseListPage caseListPage = new CaseListPage(driver);

        // Login
        loginPage.login(dotenv.get("SALESFORCE_USERNAME"), dotenv.get("SALESFORCE_PASSWORD"));
        TestLogger.log("Logged in successfully", driver);

        // Navigate to Contacts
        homePage.navigateToContacts();
        TestLogger.log("Navigated to Contacts", driver);

        // Set zoom and navigate to John Smith
        contactsPage.navigateToJohnSmith();
        TestLogger.log("Navigated to John Smith's contact page", driver);

        // Create a new case
        contactsPage.clickNewCase();
        casePage.createCase();
        String createdCaseNumber = casePage.getCaseNumber();
        TestLogger.log("Created a new case: " + createdCaseNumber, driver);

        // Navigate to Cases and search for the created case
        homePage.navigateToCases();
        caseListPage.searchForCase(createdCaseNumber);
        TestLogger.log("Searched for the new case", driver);

        // Change owner and verify
        caseListPage.changeOwner(createdCaseNumber);
        String ownerAlias = caseListPage.getOwnerAlias(createdCaseNumber);
        Assert.assertEquals(ownerAlias, "Himani P");
        TestLogger.log("Changed owner and verified", driver);

        // Open the case
        caseListPage.openCase(createdCaseNumber);
        TestLogger.log("Opened the case", driver);
    }
}