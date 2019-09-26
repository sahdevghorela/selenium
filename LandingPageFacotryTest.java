package com.example.tests;

import com.example.tests.pom.LotteryNumbersPage;
import com.example.tests.pom.factory.LandingPageFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LandingPageFacotryTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setup() {
        webDriver = SeleniumTestUtilities.getFirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("http://localhost:8080/Lottery");
    }

    @AfterEach
    public void cleanup() {
        webDriver.quit();
    }

    @Test
    public void testFactoryClass() {
        LandingPageFactory landingPageFactory = new LandingPageFactory(webDriver);
        landingPageFactory.enterSizeValue("9");
        landingPageFactory.enterRangeValue("99");
        landingPageFactory.clickSubmit();

        LotteryNumbersPage lotteryNumbersPage = new LotteryNumbersPage(webDriver);
        assertEquals("Lottery Results", lotteryNumbersPage.getTitle());
        assertTrue(lotteryNumbersPage.firstRowExists());
    }
}
