package com.prestashop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public abstract class  TestBase1 {
    //You can not make an object of this class, it only can be extended
        protected WebDriver driver;
        protected Actions actions;
        protected SoftAssert softAssert;

        @BeforeClass
public void setUpClass(){
                WebDriverManager.chromedriver().setup();
        }
        @BeforeMethod
    public void setUpMethod(){
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            softAssert = new SoftAssert();
            actions =new Actions(driver);
        }

        @AfterMethod
    public void tearDownMethod() throws InterruptedException {
            Thread.sleep(3000);
           // driver.quit();
            softAssert.assertAll();
        }


}
