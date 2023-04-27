package org.example;

import org.example.POMs.*;
import org.example.constants.dataproviders.Catalog_signin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.example.constants.messages.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.example.constants.URLs.links.*;


public class Main_Page_Test extends BaseTest{


  @Test
  public void checkRefresh(){
    WebElement head = main.getHeader();
    Actions actions = new Actions(driver);
    actions.moveByOffset(head.getLocation().getX()+1, head.getLocation().getY()+1).click();
    actions.build().perform();
    FluentWait wait = new FluentWait(driver);
    wait.until(ExpectedConditions.elementToBeClickable(head));
    Assert.assertEquals(driver.getTitle(),errors_and_messages.main_title);
  }

  @Test
  public void hoverOverRooms(){
    WebElement el = main.getReserveRoomButton();
    Actions act = new Actions(driver);
    act.moveToElement(el).perform();
    Assert.assertEquals(el.getText(),errors_and_messages.reserve_rooms);
    Assert.assertEquals(main.getResRoomBelowText(),errors_and_messages.hover_rooms);

  }

  @Test
  public void enterResRoomPage(){
    Rooms r = main.getReserveRoom();
    FluentWait wait = new FluentWait(driver).withTimeout(Duration.of(10,SECONDS));
    Assert.assertEquals(reserve_rooms , driver.getCurrentUrl());
  }

  @Test
  public void enterCatalog(){
    Catalog cat_page = main.goToCatalog();
    Assert.assertEquals(driver.getCurrentUrl(),catalog_url);
  }
  @Test
  public void reserveRoom_Catalog(){
    Rooms r = main.getReserveRoom();
    Main_Page m = r.goBack();
    Catalog c = m.goToCatalog();
    c.getSignIn().click();
    new WebDriverWait(driver,5).until((ExpectedConditions.visibilityOf(driver.findElement(By.id("aspnetForm")))));

    c.fillLogin(Catalog_signin.userID);
    c.fillPassw(Catalog_signin.password);
    c.submit();

    new WebDriverWait(driver,3);

    String alert_message = driver.switchTo().alert().getText();
    Assert.assertEquals(alert_message,errors_and_messages.alert_msg);

  }

  @Test
  public void checkLanguage(){
    String localization = main.getReserveRoom().changeLang();
    Assert.assertEquals(localization,errors_and_messages.espanol);
  }

}
