package com.assignment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AmazonTest {
    private WebDriver driver;
    private String hubURL = "";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {        
        // Retrieve the grid URL from the environment variable.
        String gridUrl = System.getenv("SELENIUM_GRID_URL");
        // Assign hubURL based on the environment variable.
        hubURL = (gridUrl == null || gridUrl.isEmpty()) ? "http://localhost:4444" : gridUrl;
        
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new RemoteWebDriver(new URL(hubURL), new ChromeOptions());
            System.out.println("Connection Established with Chrome Browser");
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new RemoteWebDriver(new URL(hubURL), new FirefoxOptions());
            System.out.println("Connection Established with Firefox Browser");
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new RemoteWebDriver(new URL(hubURL), new EdgeOptions());
            System.out.println("Connection Established with Edge Browser");
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    @Test
    public void amazonAppTest() throws InterruptedException {
        driver.get("https://www.amazon.in");
        Thread.sleep(3000);
        System.out.println("Application Executing Parallelly");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
