package com.example.tests.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LandingPageFactory {

    private WebDriver webDriver;

    @FindBy(how = How.ID, using = "size")
    private WebElement sizeElement;

    @FindBy(how = How.ID, using = "range")
    private WebElement rangeElement;

    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    public LandingPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void enterRangeValue(String value) {
        rangeElement.clear();
        rangeElement.sendKeys(value);
    }

    public void enterSizeValue(String value) {
        sizeElement.clear();
        sizeElement.sendKeys(value);
    }

    public void clickSubmit(){
        submitButton.click();
    }
}
