package com.example.tests.pom;

import org.openqa.selenium.WebDriver;

public class LotteryErrorPage {

    private WebDriver webDriver;

    public LotteryErrorPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getTitle(){
        return webDriver.getTitle();
    }
}
