package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import javax.swing.plaf.PanelUI;
import java.util.concurrent.TimeUnit;

public class AccountTests {

    WebDriver driver;
    String fullNameTop;

   @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //1. Click on Sign
       driver.findElement(By.linkText("Sign in")).click();

       //2. Login using valid username and password
       WebElement email =driver.findElement(By.id("email"));
       email.sendKeys("zzhangabatyrova@gmail.com");
       WebElement passWord =driver.findElement(By.id("passwd"));
       passWord.sendKeys("Austin2019");
       WebElement submitButton =driver.findElement(By.id("SubmitLogin"));
       submitButton.click();

       fullNameTop =driver.findElement(By.xpath("//a[@class='account']//span")).getText();

    }
    @Test
    public void loginMyAccount(){
       //Verify that title contains My account

        String actualTitle ="My account";
        String expectedTitle =driver.getTitle().trim();
        System.out.println("Actual title: "+actualTitle+" | Expected title: "+expectedTitle);
        Assert.assertTrue(expectedTitle.contains(actualTitle));

        //Verify that account holder full name is displayed next to the Sign out link


        String actualFullName ="zhansaya zhanabaeva";
        System.out.println("Actual full name: "+actualFullName+" | Expected full name: "+fullNameTop);
        Assert.assertEquals(fullNameTop, actualFullName);
    }
    @Test
    public void myPersonalInformation() throws InterruptedException {
       //Click on My personal information button
       driver.findElement(By.xpath("//a[@title='Information']//span")).click();
       Thread.sleep(3000);

       //Verify that title contains Identity
        String actualTitleIdentity  ="Identity";
        String expectedIdenTitle =driver.getTitle();

        System.out.println("Actual Identity Title: "+actualTitleIdentity+" | Expected Identity Title: "+expectedIdenTitle );
        Assert.assertTrue(expectedIdenTitle.contains(actualTitleIdentity));

        //Verify that first name and last name matches the full name on top
        String firstName =driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName =driver.findElement(By.id("lastname")).getAttribute("value");
        String fullNameOnPage =firstName+" "+lastName;
        System.out.println("Actual full name: "+fullNameTop+" | Expected full name: "+fullNameOnPage);
        Assert.assertEquals(fullNameOnPage, fullNameTop);

        //Click on save button
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form/fieldset/div[11]/button/span")).click();

        //Verify error message "The password you entered is incorrect"

        String errorMesg = driver.findElement(By.xpath("//div//ol//li")).getText();
        String actualErrorMesg ="The password you entered is incorrect.";
        System.out.println("Actual error msg: "+actualErrorMesg+" | Expected error msg: "+errorMesg);
        Assert.assertEquals(errorMesg, actualErrorMesg);

        //Click on Back to account
        driver.findElement(By.linkText("Back to your account")).click();

        //Verify title contains My account
        String actualTitleOnAccount ="My account";
        String expectedTitleOnAccount =driver.getTitle();
        System.out.println("Actual title: "+actualTitleOnAccount+" | Expected Title on Account: "+expectedTitleOnAccount);
        Assert.assertTrue(expectedTitleOnAccount.contains(actualTitleOnAccount));
    }

    @Test
    public void myAddressesTest() {
        //Click on My addresses

        driver.findElement(By.xpath("//a[@title='Addresses']")).click();
        //click on Add a new address
        driver.findElement(By.xpath("//div//a[@title='Add an address']")).click();

        String firstNameOnAddressPage = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastnameOnAddressPage = driver.findElement(By.id("lastname")).getAttribute("value");
        String fullnameOnAddressPage = firstNameOnAddressPage + " " + lastnameOnAddressPage;

        System.out.println("Actual full name: " + fullNameTop + " | Expected full name: " + fullnameOnAddressPage);
        Assert.assertEquals(fullNameTop, fullnameOnAddressPage);

        //Delete the first name
        driver.findElement(By.id("firstname")).clear();

        //Click Save
        driver.findElement(By.id("submitAddress")).click();

        //Verify error message "firstname is required"
        String errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[1]")).getText();
        String actualErrorMessage = "firstname is required.";
        System.out.println("Actual error message: " + actualErrorMessage + " | Expected error message: " + errorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage);
    }
        @AfterMethod
        public void tearDown () throws InterruptedException {
            Thread.sleep(3000);
            driver.quit();
        }


}