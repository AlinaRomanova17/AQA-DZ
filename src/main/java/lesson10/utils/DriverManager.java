package lesson10.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public final class DriverManager {

    private static WebDriver driver;
    private static boolean shutdownHookRegistered;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            registerShutdownHook();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.setExperimentalOption("detach", false);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } catch (Exception ignored) {
            try {
                driver.close();
            } catch (Exception ignoredClose) {
            }
        } finally {
            driver = null;
        }
    }

    private static void registerShutdownHook() {
        if (!shutdownHookRegistered) {
            Runtime.getRuntime().addShutdownHook(new Thread(DriverManager::quitDriver));
            shutdownHookRegistered = true;
        }
    }
}
