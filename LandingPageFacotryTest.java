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
Hi,

I hereby, give my consent to forward all medical documentation  for treatment/investigations done on me dated 15/08/2019 at East Surrey Hospital in order to support my insurance claim to sumanchaudhary516@gmail.com. She is my daughter in law and reside at address "12 Gayton court, Somers road, Reigate, RH2 9DX"

PLEASE NOTE:  As my mother is not educated therefore, I (Sahdev Ghorela) have written and read above to her. She has provided her consent for same. 

Thanks and Regards
Vimla Devi
(Thumb impression) 
