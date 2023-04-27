package org.example.POMs;

import org.example.constants.locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Rooms extends BasePage{
  protected WebDriver driver;
  protected By goback = By.xpath(Rooms_locs.goBack);
  protected By lang = By.id(Rooms_locs.language);
  public Rooms(WebDriver dr){
    driver = dr;
  }
  public Main_Page goBack(){
    driver.findElement(goback).click();
    return new Main_Page(driver);
  }
  public String changeLang(){
     new Select(driver.findElement(lang)).selectByValue("es");
     return driver.findElement(By.xpath(Rooms_locs.localization)).getText();
  }

}
