package org.example;

import org.example.POMs.Main_Page;
import org.example.constants.URLs.links;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.reporters.jq.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
  public static WebDriver driver;
  public static Main_Page main;

  @BeforeSuite
  public static void driver_init() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability("browserVersion", "67");
    chromeOptions.setCapability("platformName", "Windows XP");
    try {
      WebDriver driver = new RemoteWebDriver(new URL("http://10.116.33.195:4444"), chromeOptions);
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    driver.get(links.SUT_base_url);
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--remote-allow-origins=*");
//    System.setProperty("webdriver.chrome.driver",
//        "C:\\Users\\blilit\\Desktop\\AUA\\Testing_Fundamentals\\HW_3.2\\resources\\chromedriver.exe");
//    driver = new ChromeDriver(options);
//    driver.manage().window().maximize();
//    driver.get(links.SUT_base_url);
    Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(8));
    main = new Main_Page(driver);
  }

  @AfterMethod
  public void recordFailure(ITestResult testResult){
    if(testResult.getStatus() == ITestResult.FAILURE)
    {
      File snapshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try{
        Files.move(snapshot.toPath(), new File("src\\snapshots\\" +  testResult.getName() + ".jpg").toPath());
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }

  @AfterSuite
  public static void exit() {
    if(driver != null)
      driver.close();
  }

}
