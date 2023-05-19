package org.example.POMs;

import org.example.constants.locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Main_Page extends BasePage {
  protected WebDriver driver;
  public static By bar = By.id(Main_Page_locs.searchbar);

  public Main_Page(WebDriver dr) {
    driver = dr;
  }

  public SearchResults search(String word) {
    WebElement searchbar = driver.findElement(bar);
    searchbar.sendKeys(word);
    searchbar.sendKeys(Keys.ENTER);
    return new SearchResults(driver);
  }
}
