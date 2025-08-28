package com.conformiq.conformiq_automation3x.utilities;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.google.common.io.Files;
import org.testng.Assert;

public class PageObjectBase {
    // ThreadLocal WebDriver - thread-safe for parallel execution
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Boolean> sessionActive = ThreadLocal.withInitial(() -> false);
    
    /**
     * Gets the WebDriver for the current thread
     */
    protected WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null || !isSessionActive()) {
            // Auto-reinitialize if driver is null or session is closed
            try {
                initializeDriver();
                driver = driverThreadLocal.get();
            } catch (Exception e) {
                System.err.println("Failed to initialize WebDriver: " + e.getMessage());
                e.printStackTrace();
                Assert.fail("WebDriver initialization failed");
            }
        }
        return driver;
    }
    
    /**
     * Checks if the WebDriver session is active for the current thread
     */
    protected boolean isSessionActive() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null || !sessionActive.get()) {
            return false;
        }
        
        try {
            // Quick check to see if driver is still responsive
            driver.getWindowHandles();
            return true;
        } catch (org.openqa.selenium.WebDriverException e) {
            sessionActive.set(false);
            return false;
        }
    }
    
    /**
     * Initialize the WebDriver for the current thread
     */
    private void initializeDriver() throws MalformedURLException {
        // Clean up any existing driver first
        cleanupDriver();
        
        String conf = Configurations.browserType;
        WebDriver driver = null;
        
        if(conf.contentEquals("Mozilla")) {
            System.setProperty("webdriver.gecko.driver", Configurations.DRIVER_PATH);
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if(conf.contentEquals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", Configurations.DRIVER_PATH);
            
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_settings.popups", 0);
            options.setExperimentalOption("prefs", prefs);
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else if(conf.contentEquals("IE")) {
            System.setProperty("webdriver.ie.driver3", Configurations.DRIVER_PATH);
            driver = new InternetExplorerDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + conf);
        }
        
        driverThreadLocal.set(driver);
        sessionActive.set(true);
        System.out.println("WebDriver initialized for thread: " + Thread.currentThread().getId());
    }
    
    /**
     * Clean up the WebDriver for the current thread
     */
    private void cleanupDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                // Set shorter timeouts before quitting to avoid long waits
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
                
                // Close all windows first to ensure clean shutdown
                try {
                    driver.getWindowHandles().forEach(handle -> driver.switchTo().window(handle).close());
                } catch (Exception e) {
                    System.out.println("Warning: Could not close all windows: " + e.getMessage());
                }
                
                // Then quit the driver
                driver.quit();
            } catch (Exception e) {
                System.out.println("Warning during driver cleanup: " + e.getMessage());
            } finally {
                // Always clean up thread local variables
                driverThreadLocal.remove();
                sessionActive.set(false);
            }
        }
    }
    
    @BeforeMethod
    public void setupTest() throws MalformedURLException {
        // Ensure driver is initialized before each test method
        getDriver();
    }
    
    @AfterMethod
    public void cleanupTest() {
        // Clean up after each test method
        cleanupDriver();
    }
    
    // Keep @BeforeClass and @AfterClass for backward compatibility
    @BeforeClass
    public void setupClass() {
        // Class-level setup if needed
    }
    
    @AfterClass
    public void tearDown() {
        // Ensure driver is cleaned up after the class
        cleanupDriver();
    }

    public void navigateToPage(String url) {
        WebDriver driver = getDriver(); // This will auto-reinitialize if needed
        if (url == null) {
            driver.get(Configurations.url);
        } else {
            driver.get(url);
        }
    }

    public void getScreenshot(String location, String testId, String stepNo) {
        try {
            WebDriver driver = getDriver(); // This will auto-reinitialize if needed
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            File image = new File(location + testId + "/ScreenShots/");
            image.mkdirs();
            Files.write(screenShot, new File(image + "/" + testId + "_" + stepNo + ".png"));
        } catch (org.openqa.selenium.WebDriverException e) {
            System.out.println("Warning: Cannot take screenshot: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Static version for backward compatibility
    public static void getScreenshot(WebDriver driver, String location, String testId, String stepNo) {
        try {
            // Check if driver is active
            if (driver == null) {
                System.out.println("Warning: Cannot take screenshot, driver is null");
                return;
            }
            
            try {
                // Check if session is active
                driver.getWindowHandles();
            } catch (org.openqa.selenium.WebDriverException e) {
                System.out.println("Warning: Cannot take screenshot, driver session is not active");
                return;
            }
            
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            File image = new File(location + testId + "/ScreenShots/");
            image.mkdirs();
            Files.write(screenShot, new File(image + "/" + testId + "_" + stepNo + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error taking screenshot: " + e.getMessage());
        }
    }
}
