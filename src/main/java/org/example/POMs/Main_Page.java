package org.example.POMs;

import org.example.constants.locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


public class Main_Page extends BasePage {
  protected WebDriver driver;
  public static By header = By.xpath(Main_Page_locs.header);
  public static By rooms = By.className(Main_Page_locs.room_reserve);
  public static By select = By.id(Main_Page_locs.select_box);
  public static By bar = By.id(Main_Page_locs.bar);

  public Main_Page(WebDriver dr) {
    driver = dr;
  }

  public WebElement getHeader() {
    WebElement head = driver.findElement(header);
    return head;
  }

//  public void visibility(WebElement el, String l,int s){
//    FluentWait wait = new FluentWait(driver);
//    wait.withTimeout(Duration.of(s, SECONDS));
//    wait.until(ExpectedConditions.presenceOfElementLocated());
////    return el.
//  }
  public WebElement getReserveRoomButton(){
    return driver.findElement(rooms);
  }
  public Rooms getReserveRoom() {
    WebElement e = driver.findElement(rooms);
    e.click();
    return (new Rooms(driver));
  }
  public String getResRoomBelowText(){
    WebElement e = driver.findElement(By.xpath(Main_Page_locs.room_reserve_below));
    return e.getText();
  }
  public Catalog goToCatalog(){
    this.selectBox();
    WebElement e = driver.findElement(this.bar);
    e.click();
    e.sendKeys(Keys.ENTER);
//    new WebDriverWait(driver,20).until((ExpectedConditions.urlToBe(links.catalog_url)));
    return (new Catalog(driver));
  }

  private void selectBox() {
    Select sel = new Select(driver.findElement(select));
    sel.selectByValue(Main_Page_locs.option_value);
  }
}
