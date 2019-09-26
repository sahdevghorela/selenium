package com.example.tests.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LotteryNumbersPage {

    private WebDriver webDrive;

    public LotteryNumbersPage(WebDriver webDrive){
        this.webDrive = webDrive;
    }

    public String getTitle(){
        return webDrive.getTitle();
    }

    public boolean firstRowExists(){
        try{
            List<WebElement> firstRow = webDrive.findElements(By.xpath("html/body/table/tbody/tr[2]/td[1]/span"));
            return !firstRow.isEmpty();
        }catch(Exception e){
            return false;
        }
    }
}
