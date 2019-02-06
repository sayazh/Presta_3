package com.prestashop.tests.smoke_tests;

import com.prestashop.utilities.TestBase1;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrestaShop extends TestBase1 {
    WebDriver driver;
    String productName;
    String productPrice;
    WebElement productNameEl;



    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
         productNameEl =driver.findElement(By.linkText("Blouse"));
         productName = productNameEl.getText();
         WebElement productPriceEl =driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[2]/div/div[2]/div[1]/span"));
          productPrice=productPriceEl.getText();
    }

    @Test
    public void price(){
        productNameEl.click();
        String productNameonPage =driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        String productPricePage =driver.findElement(By.id("our_price_display")).getText();
        System.out.println("Actual product name: "+productName+" | Expected product name: "+productNameonPage);
        System.out.println("Actual product price: "+productPrice+" | Expected product price: "+productPricePage);

        Assert.assertEquals(productName, productNameonPage);
        Assert.assertEquals(productPrice, productPricePage);
    }

    @Test
    public void details(){
        productNameEl.click();
        WebElement quantity =driver.findElement(By.id("quantity_wanted"));
        String actualQuantity =quantity.getAttribute("value");
        String expectedQuantity ="1";
        WebElement size =driver.findElement(By.id("group_1"));
        Select select = new Select(size);
        String actualSize = select.getFirstSelectedOption().getText();
        String expectedSize ="S";
        System.out.println("Expected quantity: "+expectedQuantity+" | Actual quantity: "+actualQuantity);
        System.out.println("Expected size: "+expectedSize+" | Actual size: "+actualSize);
        Assert.assertEquals(actualQuantity, expectedQuantity);
        Assert.assertEquals(actualSize, expectedSize);
    }

     @Test
     public void addToCart() throws InterruptedException {
     productNameEl.click();
     driver.findElement(By.xpath("//button[@name='Submit']//span")).click();
     String expectedConfirmationMsg ="Product successfully added to your shopping cart";
     WebElement confirmMsg = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
     String actualConfirmMsg = confirmMsg.getAttribute("textContent").trim();
         System.out.println("Expected confirm msg: "+expectedConfirmationMsg+" | Actual msg: "+actualConfirmMsg);
         Assert.assertEquals(expectedConfirmationMsg, actualConfirmMsg);

         Thread.sleep(3000);
         WebElement defaultQuantity =driver.findElement(By.id("layer_cart_product_quantity"));
         String quantityDefault =defaultQuantity.getText();
         String actualDefaultQuantity ="1";
         Assert.assertEquals(actualDefaultQuantity, quantityDefault);

         String defaultSize =driver.findElement(By.id("layer_cart_product_attributes")).getText();
         String defSize =defaultSize.substring(7);
         String expectedDefaultSize ="S";
         System.out.println("Expected def size: " +expectedDefaultSize+"   Actual size: "+defSize);
         Assert.assertEquals(expectedDefaultSize, defSize);
 }



}
