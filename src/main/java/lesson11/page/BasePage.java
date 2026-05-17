package lesson11.page;

import lesson11.utils.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebDriverWait shortWait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected void scrollTo(WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }

    protected void executeJs(String script, Object... args) {
        ((JavascriptExecutor) driver).executeScript(script, args);
    }
}
