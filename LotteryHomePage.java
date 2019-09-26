package com.example.tests.pom;

import com.example.tests.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.Map;

import static com.example.tests.LocatorHelper.get;

public class LotteryHomePage {

    private WebDriver webDriver;

    public LotteryHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void clearSizeField(){
        webDriver.findElement(get("landingPage.field.size")).clear();
    }

    public void clearRangeField() {
        webDriver.findElement(get("landingPage.field.range")).clear();
    }

    public void sendKeysToSizeField(String value) {
        webDriver.findElement(get("landingPage.field.size")).sendKeys(value);
    }
    public void sendKeysToRangeField(String value) {
        webDriver.findElement(get("landingPage.field.range")).sendKeys(value);
    }

    public void submitPage() {
        webDriver.findElement(get("landingPage.button.submit")).click();
    }


}
