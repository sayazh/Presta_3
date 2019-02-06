package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Random;

public class PrestaShopII extends TestBase1 {

    @Ignore
    @Test(priority = 1)
    public void Registration() throws InterruptedException {
        //Open browser
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();

        //Click Sign in link
        driver.findElement(By.xpath("//a[@class='login']")).click();

        //Enter new valid email to the email field
        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.sendKeys("aibek_risbek@gmail.com");

        //Click on create account
        WebElement createAccountButton = driver.findElement(By.xpath("//button[@class='btn btn-default button button-medium exclusive']"));
        createAccountButton.click();

        //Verify that that email link displays current email
        Thread.sleep(3000);
        WebElement emailLink = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        String currentEmail = emailLink.getAttribute("value");

        String actualEmail = "aibek_risbek@gmail.com";
        System.out.println("Actual email: " + actualEmail + " | Expected email: " + currentEmail);
        Assert.assertEquals(actualEmail, currentEmail);

        //Fill out all the required steps
        WebElement genderTitle = driver.findElement(By.xpath("//input[@id='id_gender2']"));
        genderTitle.click();
        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        firstNameInput.sendKeys("zhansaya");
        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        lastNameInput.sendKeys("rysbek");

        WebElement passWdInput = driver.findElement(By.id("passwd"));
        passWdInput.sendKeys("Austin2018");
        WebElement day = driver.findElement(By.id("days"));
        day.click();
        Select select1 = new Select(day);
        select1.selectByIndex(3);
        WebElement month = driver.findElement(By.id("months"));
        month.click();
        Select select2 = new Select(month);
        select2.selectByIndex(4);
        WebElement year = driver.findElement(By.id("years"));
        year.click();
        Select select3 = new Select(year);
        select3.selectByIndex(33);
        driver.findElement(By.id("newsletter")).click();

        WebElement company = driver.findElement(By.id("company"));
        company.sendKeys("Apple");
        WebElement streetAddress = driver.findElement(By.id("address1"));
        streetAddress.sendKeys("15918 Windermere Drive");
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Austin");
        WebElement state = driver.findElement(By.xpath("//select[@id='id_state']"));
        state.click();

        Select select4 = new Select(state);
        select4.selectByIndex(44);

        WebElement zipcode = driver.findElement(By.id("postcode"));
        zipcode.sendKeys("78660");
        WebElement mobileNumber = driver.findElement(By.id("phone_mobile"));
        mobileNumber.sendKeys("+19183303588");

        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span"));
        registerButton.click();

    }

    @Ignore
    @Test(priority = 2)
    public void personInfoTest() {

        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement signInButton = driver.findElement(By.xpath("//div//a[@class='login']"));
        signInButton.click();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("aibek_risbek@gmail.com");
        WebElement passWord = driver.findElement(By.id("passwd"));
        passWord.sendKeys("Austin2018");
        WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
        submitLogin.click();
        //Verify that correct first and last name is displayed correctly on top right
        String fullName = driver.findElement(By.xpath("//a[@class='account']//span")).getText();
        String fullActualName = "zhansaya rysbek";
        System.out.println("Actual full name: " + fullActualName + " | Expected full name: " + fullName);
        Assert.assertEquals(fullName, fullActualName);

        //Click on My personalInformation
        WebElement personInfo = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span"));
        personInfo.click();

        //Verify that personal information displayed correctly
        WebElement personInfoCheck = driver.findElement(By.xpath("//h1[@class='page-subheading']"));
        String displayedInfo = personInfoCheck.getText();

        String actualPersonalInfo = "YOUR PERSONAL INFORMATION";
        System.out.println("Expected displayed information: " + displayedInfo + " | Actual information: " + actualPersonalInfo);
        Assert.assertEquals(displayedInfo, actualPersonalInfo);

        //Click on Back to your account
        WebElement backToAccount = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/a/span"));
        backToAccount.click();

        //Click on My Addresses verify that address information is displayed correctly
        WebElement myAddressesbutton = driver.findElement(By.xpath("//a[@title='Addresses']//span"));
        myAddressesbutton.click();

        String inputCompanyName = "Apple";
        String displayedCompanyName = driver.findElement(By.xpath("//li//span[@class='address_company']")).getText();

        String inputStreetName = "15918 Windermere Drive";
        String displayedStreet = driver.findElement(By.xpath("//li//span[@class='address_address1']")).getText();

        String inputCityName = "Austin";
        String displayedCity = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[1]")).getText();
        String checkCity = displayedCity.substring(0, displayedCity.length() - 1);

        String selectStateName = "South Dakota";
        String displayedState = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[2]")).getText();

        String inputZipCode = "78660";
        String displayedZipCode = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[5]/span[3]")).getText();

        String selectedCountry = "United States";
        String displayedCountry = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[6]/span")).getText();

        String inputPhoneMobile = "+19183303588";
        String displayedPhoneMobile = driver.findElement(By.xpath("//li//span[@class='address_phone_mobile']")).getText();

        System.out.println("Input company name: " + inputCompanyName + " | Displayed company: " + displayedCompanyName);
        Assert.assertEquals(inputCompanyName, displayedCompanyName);

        System.out.println("Input street: " + inputStreetName + " | Displayed street name: " + displayedStreet);
        Assert.assertEquals(inputStreetName, displayedStreet);

        System.out.println("Input city name: " + inputCityName + " | Displayed city name: " + checkCity);
        Assert.assertEquals(inputCityName, checkCity);

        System.out.println("Input state: " + selectStateName + " | Displayed state: " + displayedState);
        Assert.assertEquals(selectStateName, displayedState);

        System.out.println("Input zipcode: " + inputZipCode + " | Displayed zipcode: " + displayedZipCode);
        Assert.assertEquals(inputZipCode, displayedZipCode);

        System.out.println("Select country: " + selectedCountry + " | Displayed country: " + displayedCountry);
        Assert.assertEquals(selectedCountry, displayedCountry);

        System.out.println("Input mobile number: " + inputPhoneMobile + " | Displayed mobile number: " + displayedPhoneMobile);
        Assert.assertEquals(inputPhoneMobile, displayedPhoneMobile);

        //Click on sign out link
        WebElement signOutLink = driver.findElement(By.xpath("//div//a[@class='logout']"));
        signOutLink.click();

        //Login using using the email and password if the current user
        driver.findElement(By.id("email")).sendKeys("aibek_risbek@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Austin2018");
        driver.findElement(By.id("SubmitLogin")).click();
        String fullNameOnTop = driver.findElement(By.xpath("//a[@class='account']//span")).getText();
        System.out.println("Full Name On top: " + fullNameOnTop + " | Actual full name: " + fullActualName);
        Assert.assertEquals(fullNameOnTop, fullActualName);
    }

    @Test(priority = 3)
    public void carDetails() throws InterruptedException {
        //Open browser
        //Go to http://automationpractice.com/index.php
        driver.get("http://automationpractice.com/index.php");


        //Click on any product that is not on sale
        driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img")).click();

        //Enter a random quantity between 2 and 5
        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < 1; i++) {
            randomNumber = random.nextInt(2) + 3;
            System.out.println("Random number: " + randomNumber);
            WebElement clickQuantity = driver.findElement(By.xpath("//span//i[@class='icon-plus']"));
            switch (randomNumber) {
                case 2:
                    clickQuantity.click();
                    break;
                case 3:
                    clickQuantity.click();
                    clickQuantity.click();
                    break;
                case 4:
                    clickQuantity.click();
                    clickQuantity.click();
                    clickQuantity.click();
                    break;
                case 5:
                    clickQuantity.click();
                    clickQuantity.click();
                    clickQuantity.click();
                    clickQuantity.click();
                    break;
                default:
                    break;
            }
        }
        //Select different size
        Thread.sleep(3000);
        Select diffSize = new Select(driver.findElement(By.xpath("//div[@class='selector']//select")));
        diffSize.selectByVisibleText("M");

        //Click on add to cart
        driver.findElement(By.xpath("//button[@class='exclusive']//span")).click();
        String firstProduct = driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();

        //Verify confirmation message Product successfully added to your shopping cart
        String expectedConfirmationMsg = "Product successfully added to your shopping cart";
        WebElement actualConfirmationMsg = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
        actualConfirmationMsg.click();
        String actualMessage = actualConfirmationMsg.getText();
        System.out.println("Expected confirmation msg: " + expectedConfirmationMsg + " | Actual confirmation msg: " + actualMessage);
        Assert.assertEquals(expectedConfirmationMsg, actualMessage);

        // Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//div//span[@class='cross']")).click();

        //Click on the company logo
        driver.findElement(By.xpath("//a//img[@class='logo img-responsive']")).click();

        //Click on any product that is on sale
        driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[5]/div/div[1]/div/a[1]/img")).click();

        System.out.println("============= PRODUCT ON SALE ================");
        //Enter a random quantity between 2 and 5
        Random random2 = new Random();
        int randomNumber2;
        for (int i = 0; i < 1; i++) {
            randomNumber2 = random.nextInt(2) + 3;
            System.out.println("Random number: " + randomNumber2);
            WebElement clickQuantity2 = driver.findElement(By.xpath("//span//i[@class='icon-plus']"));
            switch (randomNumber2) {
                case 2:
                    clickQuantity2.click();
                    break;
                case 3:
                    clickQuantity2.click();
                    clickQuantity2.click();
                    break;
                case 4:
                    clickQuantity2.click();
                    clickQuantity2.click();
                    clickQuantity2.click();
                    break;
                case 5:
                    clickQuantity2.click();
                    clickQuantity2.click();
                    clickQuantity2.click();
                    clickQuantity2.click();
                    break;
                default:
                    break;
            }
        }
        //Select different size
        Thread.sleep(2000);
        Select diffSize2 = new Select(driver.findElement(By.xpath("//div[@class='selector']//select")));
        diffSize2.selectByVisibleText("L");

        //Click on add to cart
        driver.findElement(By.xpath("//button[@class='exclusive']//span")).click();
        String secondProduct = driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();

        //Verify confirmation message Product successfully added to your shopping cart
        WebElement actualConfirmationMsg2 = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
        actualConfirmationMsg2.click();
        String actualMessage2 = actualConfirmationMsg2.getText();
        String expectedConfirmationMsg2 = "Product successfully added to your shopping cart";
        System.out.println("Expected confirmation msg: " + expectedConfirmationMsg2 + " | Actual msg: " + actualMessage2);
        Assert.assertEquals(actualMessage2, expectedConfirmationMsg2);

        //Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@title='Close window']")).click();

        //Hover over the Cart icon
        WebElement cartIcon = driver.findElement(By.xpath("//div[@class='shopping_cart']//a"));
        actions.moveToElement(cartIcon).perform();
        String totalProduct = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[2]")).getText();
        System.out.println("==========================");
        System.out.println(firstProduct+ "" + secondProduct + "" + totalProduct);
        //Verify that correct total displayed
    }
}
