import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;

public class SauceDemoLocators {
    WebDriver driver;

    public static void main(String[] args) {
        Product bag = new Product();
        Product.addPrice(29.99);
        ProductName name = new ProductName();
        name.setName("Sauce Labs Backpack");
        System.out.println(name.name + Product.productsPrice);
    }

    @BeforeTest

    public void preCondition() {
        System.setProperty("webdriver.chrome.driver", "src\\chromedriver.exe.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void sauceDemoLocators() {
        //check items and login
        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"user-name\"]")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"password\"]")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"login-button\"]")).isEnabled());
        //login
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void searchElem() {
        driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img"));
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div"));
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"));
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).isDisplayed());
    }

    @AfterTest
    public void postTest() {
        //driver.quit();
    }

    public static class Product {
        public static double productsPrice = 0;

        public static void addPrice(double productsPrice) {
            Product.productsPrice = Product.productsPrice + productsPrice;
        }
    }
    public static class ProductName{
        private String name = "Sauce Labs Backpack";
        public void setName(String name){
            this.name = name;
        }
    }
}