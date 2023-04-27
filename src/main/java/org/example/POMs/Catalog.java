package org.example.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.example.constants.locators.Catalog_locs;
public class Catalog extends BasePage{
  private WebDriver driver;
  private By signin = By.id(Catalog_locs.signIn);
  private By login = By.id(Catalog_locs.username);
  private By pass = By.id(Catalog_locs.password);
  private By submit = By.id(Catalog_locs.submit);
  public Catalog(WebDriver d){
    driver = d;
  }
  public WebElement getSignIn(){
    return (driver.findElement(signin));
  }
  public void fillLogin(String id){
     (driver.findElement(login)).sendKeys(id);
  }
  public void fillPassw(String passw){
     (driver.findElement(pass)).sendKeys(passw);
  }
  public void submit(){
    driver.findElement(submit).sendKeys(Keys.ENTER);
  }

}
