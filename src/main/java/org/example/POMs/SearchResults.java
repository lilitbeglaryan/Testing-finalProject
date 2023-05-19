package org.example.POMs;

import org.example.constants.locators.SearchResults_locs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.constants.messages.errors_and_messages;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends BasePage{
  private WebDriver driver;
  private By tv = By.xpath(SearchResults_locs.TV_item);
  private By heart = By.xpath(SearchResults_locs.wishlist);
  private By wishlist_window = By.xpath((SearchResults_locs.wishlist_window));
  private By wishlist_heart = By.xpath(SearchResults_locs.wishlist_heart);
  private By count = By.className(SearchResults_locs.wishlist_count);
  private By remove_wishlist = By.xpath(SearchResults_locs.delete_item);
  private By cart = By.xpath(SearchResults_locs.cart);
  private By remove_cart = By.xpath(SearchResults_locs.remove_from_cart);
  private By cart_items = By.className(SearchResults_locs.cart_count);
  private By sort = By.xpath(SearchResults_locs.sort_options);
  private By price_tags = By.className(SearchResults_locs.prices);
  public SearchResults(WebDriver d){
    driver = d;
  }

  public String  getTv(){
    return driver.findElement(tv).getText();
  }
  public int addTV_wishlist(){
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(tv)).perform();
    driver.findElement(heart).click();
    WebDriverWait wait = new WebDriverWait(driver,15);
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(wishlist_window)));
    new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(wishlist_window));

    int n_items = Integer.valueOf(driver.findElement(count).getText());
    return  n_items;
  }

  public int delete_wishlist(){
    driver.findElement(wishlist_heart).click();
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.presenceOfElementLocated(remove_wishlist)).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(wishlist_heart));
    return Integer.valueOf(driver.findElement(count).getText());
  }

  public int add_cart(){
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(tv)).perform();
    driver.findElement(By.xpath(SearchResults_locs.add_to_cart)).click();

    return Integer.valueOf(driver.findElement(cart_items).getText());
  }

  public int remove_cart(){

    driver.findElement(cart).click();
    new WebDriverWait(driver, 3);
    driver.findElement(remove_cart).click();
    new WebDriverWait(driver, 3);
    return Integer.valueOf(driver.findElement(cart_items).getText());
  }

  public List<Integer> sortByPrices(boolean asc){
    Select select = new Select(driver.findElement(sort));
    if(asc == true){
      select.selectByVisibleText(errors_and_messages.sort_asc);
    }
    else{
      select.selectByVisibleText(errors_and_messages.sort_desc);
    }

    List<WebElement> all_prices_on_the_page = driver.findElements(price_tags);
    List<Integer> l = new ArrayList<Integer>();
    for (WebElement price : all_prices_on_the_page)
    {
      int pr = Integer.valueOf(price.getText().substring(0,price.getText().length() -2 )); // delete the space ad dram sign
      l.add(pr);
    }

    return  l;
  }

}
