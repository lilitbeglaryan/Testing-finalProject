package org.example.POMs;

import org.openqa.selenium.WebDriver;

public class BasePage {

  public BasePage(){

  }
  public WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

}