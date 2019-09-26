package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class LandingPageTest {
   private WebDriver driver;
   private String baseUrl;
   private boolean acceptNextAlert = true;
   private long startTime;
   private long endTime;
   private StringBuilder errors;

   @BeforeEach
   public void setUp() {
      driver = SeleniumTestUtilities.getFirefoxDriver();
      baseUrl = "http://localhost:8080/Lottery";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      startTime = System.currentTimeMillis();
      errors = new StringBuilder();
   }

   @Test
   public void testLandingPage() {
      driver.get(baseUrl + "/");
      delayedAssertEquals("Lottery", driver.getTitle());
  
      delayedAssertEquals("Welcome to our Lottery System", driver.findElement(By.cssSelector("h1")).getText());
      delayedAssertTrue(isElementPresent(By.id("size")));
      delayedAssertTrue(isElementPresent(By.id("range")));
 
      delayedAssertEquals("Pick My Lucky Numbers", driver.findElement(By.cssSelector("input[type=\"submit\"]")).getAttribute("value"));
      delayedAssertEquals("yes", driver.findElement(By.xpath("html/body/div[2]/form/input[3]")).getAttribute("value"));
   }

   private void delayedAssertTrue(boolean truth) {
      try {
         assertTrue(truth);
      } catch (Error e) {
         errors.append(e.getMessage());
      }
   }

   private void delayedAssertEquals(String expected, String actual) {
      try {
         assertEquals(expected,actual);
      } catch (Error e) {
        errors.append(e.getMessage());
      }
   }

   @Test
   public void testInvalidSize() {
      driver.get(baseUrl + "/");
      driver.findElement(By.id("size")).clear();
      driver.findElement(By.id("size")).sendKeys("0");
      driver.findElement(By.id("range")).clear();
      driver.findElement(By.id("range")).sendKeys("12");
      driver.findElement(By.cssSelector("input[type='submit']")).click();

      delayedAssertEquals("Lottery Error",driver.getTitle());
      System.out.println(driver.findElement(By.cssSelector("h3.center")).getText());
      delayedAssertTrue(driver.findElement(By.cssSelector("h3.center")).getText().matches("^The value supplied for the Number to pick is invalid!$"));
   }
   @Test
   public void missingRequiredParametersTest() {
      driver.get(baseUrl);
      driver.findElement(By.id("range")).clear();
      driver.findElement(By.id("size")).clear();
      driver.findElement(By.cssSelector("input[type='submit']")).click();

      delayedAssertTrue(this.isAlertPresent());
      String alertText = this.closeAlertAndGetItsText();
      System.out.println(alertText);
   }

   @Test
   public void nextPageWithNumbersTest() {
      driver.get(baseUrl);
      driver.findElement(By.id("size")).clear();
      driver.findElement(By.id("size")).sendKeys("6");
      driver.findElement(By.id("range")).clear();
      driver.findElement(By.id("range")).sendKeys("16");
      driver.findElement(By.cssSelector("input[type='submit']")).click();

      //Wait wait = new WebDriverWait(driver,5,1);
     // wait.until(ExpectedConditions.titleContains("Lottery Results"));

      delayedAssertEquals("Lottery Results", driver.getTitle());
      if(!isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[1]"))){
         fail("First set of numbers not found");
      }
      if(isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[2]"))){
         fail("Second set of numbers found but should not be there");
      }

      List<WebElement> firstRow = driver.findElements(By.xpath("html/body/table/tbody/tr[2]/td[1]/span"));
      //List<WebElement> firstRow = driver.findElements(By.xpath("td.span"));
      delayedAssertTrue(6 == firstRow.size());
     // String cellrow1 = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[1]/span[@id='0-0']")).getText();
      String cellrow1 = driver.findElement(By.cssSelector("span[id='0-0']")).getText();
      String cellrow2 = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[1]/span[@id='0-1']")).getText();

      WebElement moreNumbers = driver.findElement(By.cssSelector("input[type='submit'"));
      moreNumbers.click();

      delayedAssertTrue(isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[1]")));
      delayedAssertTrue(isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[2]")));
      delayedAssertFalse(isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[3]")));
      delayedAssertFalse(isElementPresent(By.xpath("html/body/table/tbody/tr[2]/td[4]")));

      List<WebElement> secondRow = driver.findElements(By.xpath("html/body/table/tbody/tr[2]/td[1]/span"));
      delayedAssertTrue(6 == secondRow.size());
      String cell2row1 = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[2]/span[@id='1-0']")).getText();
      String cell2row2 = driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[2]/span[@id='1-1']")).getText();
      delayedAssertEquals(cellrow1,cell2row1);
      delayedAssertEquals(cellrow2,cell2row2);
   }

   private void delayedAssertFalse(boolean lie) {
      try {
         assertFalse(lie);
      } catch (Exception e) {
         errors.append(e.getMessage());
      }
   }

   @Test
   public void screenShotTest() throws IOException {
      TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
      driver.get(baseUrl);

      delayedAssertEquals("Lottery",driver.getTitle());
      File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
      Path sourcePath = sourceFile.toPath();
      Path targetPath = Paths.get("C:\\Users\\Administrator\\Desktop\\TT3615 Selenium\\StudentWork\\code\\Creating and Running a WebDriver Test\\lab-code\\src\\test\\com\\example\\tests\\landing.png");
      delayedAssertFalse(targetPath.toFile().exists());
      Files.copy(sourcePath,targetPath);

      delayedAssertTrue(targetPath.toFile().exists());

   }

   private boolean isElementPresent(By by) {
      try {
         driver.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   @AfterEach
   public void tearDown() {
      endTime = System.currentTimeMillis();
      long diff = endTime - startTime;
      System.out.println("Total time taken in milliseconds is " + diff);
      driver.quit();
   }

   private boolean isAlertPresent() {
      try {
         driver.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
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
