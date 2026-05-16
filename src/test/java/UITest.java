import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }

    @Test
    void verifyOnlineTopUpBlock() {
        final By BLOCK_TITLE = By.xpath("//section[contains(@class, 'pay')]//h2");
        final By PAYMENT_LOGOS = By.xpath("//div[contains(@class, 'pay__partners')]//img");
        final By DETAILS_LINK = By.xpath("//section[contains(@class, 'pay')]//a[contains(text(), 'Подробнее о сервисе')]");
        final By PHONE_INPUT = By.xpath("//*[@id='connection-phone']");
        final By AMOUNT_INPUT = By.xpath("//*[@id='connection-sum']");
        final By EMAIL_INPUT = By.xpath("//*[@id='connection-email']");
        final By CONTINUE_BUTTON = By.xpath("//form[@id='pay-connection']//button[contains(text(), 'Продолжить')]");
        final By PAYMENT_FRAME = By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'checkout')]");

        acceptCookies();

        wait.until(ExpectedConditions.presenceOfElementLocated(PHONE_INPUT));
        WebElement blockTitle = wait.until(ExpectedConditions.presenceOfElementLocated(BLOCK_TITLE));
        scrollToElement(blockTitle);
        assertEquals(
                "Онлайн пополнение без комиссии",
                blockTitle.getText().replace("\n", " ").trim()
        );

        WebElement partners = driver.findElement(By.cssSelector("section.pay .pay__partners"));
        scrollToElement(partners);
        wait.until(driver -> driver.findElements(PAYMENT_LOGOS).size() == 5);

        List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);
        assertEquals(5, logos.size());
        hasLogo(logos, "Visa");
        hasLogo(logos, "Verified By Visa");
        hasLogo(logos, "MasterCard");
        hasLogo(logos, "MasterCard Secure Code");
        hasLogo(logos, "Белкарт");

        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
        scrollToElement(detailsLink);
        assertEquals("Подробнее о сервисе", detailsLink.getText().trim());
        assertTrue(
                detailsLink.getAttribute("href")
                        .contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/")
        );

        detailsLink.click();
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(PHONE_INPUT));
        scrollToPayForm();

        WebElement phoneInput = driver.findElement(PHONE_INPUT);
        scrollToElement(phoneInput);
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement amountInput = driver.findElement(AMOUNT_INPUT);
        scrollToElement(amountInput);
        amountInput.clear();
        amountInput.sendKeys("102");

        WebElement emailInput = driver.findElement(EMAIL_INPUT);
        scrollToElement(emailInput);
        emailInput.clear();
        emailInput.sendKeys("alinkafm@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        scrollToElement(continueButton);
        continueButton.click();

        assertTrue(
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.urlContains("checkout"),
                        ExpectedConditions.urlContains("payment"),
                        ExpectedConditions.presenceOfElementLocated(PAYMENT_FRAME)
                )) != null,
                "Кнопка «Продолжить» не открыла страницу оплаты"
        );
    }

    private void acceptCookies() {
        List<WebElement> cookies = driver.findElements(By.id("cookie-agree"));
        if (!cookies.isEmpty() && cookies.get(0).isDisplayed()) {
            cookies.get(0).click();
            return;
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))).click();
        } catch (TimeoutException ignored) {
        }
    }

    private void hasLogo(List<WebElement> logos, String altText) {
        boolean found = logos.stream()
                .anyMatch(logo -> altText.equals(logo.getAttribute("alt")));
        assertTrue(found, "Логотип не найден: " + altText);
    }

    private void scrollToPayForm() {
        WebElement payForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pay-connection")));
        scrollToElement(payForm);
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element
        );
    }
}
