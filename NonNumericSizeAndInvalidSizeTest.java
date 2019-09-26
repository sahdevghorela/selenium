package com.example.tests;

import com.beust.jcommander.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonNumericSizeAndInvalidSizeTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;

    @BeforeEach
    public void setUp() {
        driver = SeleniumTestUtilities.getFirefoxDriver();
        baseUrl = "http://localhost:8080/Lottery";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"data.csv"}, numLinesToSkip = 1)
    //@ValueSource(strings = {"abc","0"})
    public void testInvalidValueofSize(String sizeValue) {
        driver.get(baseUrl);

        assertEquals("Lottery", driver.getTitle());

        driver.findElement(By.id("size")).clear();
        driver.findElement(By.id("size")).sendKeys(sizeValue);
        driver.findElement(By.id("range")).clear();
        driver.findElement(By.id("range")).sendKeys("12");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String expected = "Please tell me the Max Range of Lottery picks you need[\\s\\S]*";
        String actual = closeAlertAndGetItsText();
        assertTrue(actual.matches(expected));

    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
