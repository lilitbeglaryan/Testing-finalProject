package org.example.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  public BasePage(){

  }
  public WebDriver driver;
//  private WebDriverWait wait;

  public BasePage(WebDriver driver) {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\blilit\\Desktop\\AUA\\Testing_Fundamentals\\HW_3.2\\resources\\chromedriver.exe");
    this.driver = driver;

  }
//  public WebElement getElement(By locator) {
//    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//  }


}