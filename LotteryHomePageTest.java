package com.example.tests;

import com.example.tests.pom.LotteryErrorPage;
import com.example.tests.pom.LotteryHomePage;
import com.example.tests.pom.LotteryNumbersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LotteryHomePageTest {

    private static final String BASE_URL = "http://localhost:8080/Lottery";
    private WebDriver webDriver;

    @BeforeEach
    public void setup() {
        webDriver = SeleniumTestUtilities.getFirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(BASE_URL);
    }

    @AfterEach
    public void cleanUp() {
        webDriver.close();
    }

    @Test
    public void validRangeAndSizeValuesTakesToLotteryNumbersPage() {
        LotteryHomePage homePage = new LotteryHomePage(webDriver);
        homePage.clearRangeField();
        homePage.clearSizeField();
        homePage.sendKeysToSizeField("9");
        homePage.sendKeysToRangeField("88");
        homePage.submitPage();

        LotteryNumbersPage lotteryNumbersPage = new LotteryNumbersPage(webDriver);
        assertEquals("Lottery Results", lotteryNumbersPage.getTitle());
        assertTrue(lotteryNumbersPage.firstRowExists());

    }

    @Test
    public void putInvalidValueOf0ToSizeAndRangeTakesToErrorPage() {
        LotteryHomePage homePage = new LotteryHomePage(webDriver);
        homePage.clearRangeField();
        homePage.clearSizeField();
        homePage.sendKeysToSizeField("0");
        homePage.sendKeysToRangeField("0");
        homePage.submitPage();

        LotteryErrorPage errorPage = new LotteryErrorPage(webDriver);
        assertEquals("Lottery Error", errorPage.getTitle());
    }

}
