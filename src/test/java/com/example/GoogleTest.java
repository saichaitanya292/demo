package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GoogleTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReports/index.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest("Google Title Test");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=chrome");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup(); 
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        test.info("Navigated to Google");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        String title = driver.getTitle();
        test.info("Page title: " + title);
        System.out.println("Title: " + title);
        assert title.contains("Google");
        test.pass("Title contains 'Google'");
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
        if (driver != null) driver.quit();
    }
}