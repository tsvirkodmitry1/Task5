import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SauceDemoLocators {
    WebDriver driver;


    private String userNameField;
    private String passwordField;
    private String loginButton;
    private String loginStandard;
    private String passwordKey;
    private String immageProduct;
    private String productName;
    private String productDetails;
    private String productPrice;
    private String buttonAddToCart;

    public void listXpaths() throws IOException {
        File file = new File("SauceDemoLocators.txt");
        Scanner reader = new Scanner(file);
        String line = reader.nextLine();
        String[] xpathName = line.split(" ");
        reader.close();
        System.out.println(Arrays.toString(xpathName));
        userNameField = xpathName[0];
        passwordField = xpathName[1];
        loginButton = xpathName[2];
        loginStandard = xpathName[3];
        passwordKey = xpathName[4];
        immageProduct = xpathName[5];
        productName = xpathName[6];
        productDetails = xpathName[7];
        productPrice = xpathName[8];
        buttonAddToCart = xpathName[9];
    }

    @BeforeTest

    public void preCondition() {
        System.setProperty("webdriver.chrome.driver", "src\\chromedriver.exe.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @Test
    public void sauceDemoLocators() throws IOException {
        //check items and login
        listXpaths();
        WebElement userName = driver.findElement(By.xpath(loginStandard));
        String usersName = userName.getAttribute("innerText");
        String[] firstUserName = usersName.split("\n");
        System.out.println(Arrays.toString(firstUserName));
        WebElement password = driver.findElement(By.xpath(passwordKey));
        String passwordForLogin = password.getAttribute("innerText");
        String[] onePassword = passwordForLogin.split("\n");
        System.out.println(Arrays.toString(onePassword));

        WebElement userFieldName = driver.findElement(By.xpath(userNameField));
        userFieldName.click();
        userFieldName.sendKeys(firstUserName[1]);
        WebElement fieldPassword = driver.findElement(By.xpath(passwordField));
        fieldPassword.click();
        fieldPassword.sendKeys(onePassword[1]);
        Assert.assertTrue(driver.findElement(By.xpath(userNameField)).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath(passwordField)).isEnabled());
        WebElement login = driver.findElement(By.xpath(loginButton));
        login.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void searchElem() {
        driver.findElement(By.xpath(immageProduct));
        WebElement nameProduct = driver.findElement(By.xpath(productName));
        driver.findElement(By.xpath(productDetails));
        WebElement price = driver.findElement(By.xpath(productPrice));
        driver.findElement(By.xpath(buttonAddToCart));
        Assert.assertTrue(driver.findElement(By.xpath(immageProduct)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(productName)).getText().equalsIgnoreCase("Sauce Labs Backpack"));
        Assert.assertTrue(driver.findElement(By.xpath(productDetails)).getText().equalsIgnoreCase("carry.allTheThings() with the sleek, " +
                "streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."));
        Assert.assertTrue(driver.findElement(By.xpath(productPrice)).getText().equalsIgnoreCase("$29.99"));
        Assert.assertTrue(driver.findElement(By.xpath(buttonAddToCart)).isEnabled());
        System.out.println(nameProduct.getText());
        System.out.println(price.getText());

    }

    @AfterTest
    public void postTest() {
        driver.quit();
    }
}

