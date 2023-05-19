package org.example;

import com.google.common.collect.Ordering;
import org.example.POMs.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.example.constants.messages.*;
import org.testng.annotations.Test;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main_Page_Test extends BaseTest{

  private Main_Page main = new Main_Page(driver);
  private SearchResults second_page;

  @Test
  public void checkTVsearch(){
    second_page = main.search(errors_and_messages.searchword);
    Assert.assertEquals(second_page.getTv(),errors_and_messages.tv_name);
  }

  @Test
  public void add_remove_wishlist(){
    int n = second_page.addTV_wishlist();
    Assert.assertEquals(n,1);
    n = second_page.delete_wishlist();
    Assert.assertEquals(n,0);
  }

  @Test
  public void add_remove_cart(){
    int n = second_page.add_cart();
    Assert.assertEquals(n,1);
    n = second_page.remove_cart();
    Assert.assertEquals(n,0);
  }

  @Test
  public void sortPrices(){
    List<Integer> pricesAsc = second_page.sortByPrices(true);
    List<Integer> pricesDesc = second_page.sortByPrices(false);

    Assert.assertTrue(Ordering.natural().isOrdered(pricesAsc));
    List<Integer> sortedListDesc =
        pricesDesc.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    Assert.assertEquals(pricesDesc, sortedListDesc);



  }
}
