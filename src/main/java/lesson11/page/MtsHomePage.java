package lesson11.page;

import lesson11.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MtsHomePage extends BasePage {

    @FindBy(css = "section.pay")
    private WebElement payBlock;

    @FindBy(xpath = "//section[contains(@class, 'pay')]//h2")
    private WebElement payBlockTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay__partners')]//img")
    private List<WebElement> partnerLogos;

    @FindBy(xpath = "//section[contains(@class, 'pay')]//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsAboutServiceLink;

    @FindBy(id = "pay")
    private WebElement paymentTypeSelect;

    @FindBy(id = "pay-connection")
    private WebElement connectionForm;

    @FindBy(id = "connection-phone")
    private WebElement connectionPhoneInput;

    @FindBy(id = "connection-sum")
    private WebElement connectionSumInput;

    @FindBy(id = "connection-email")
    private WebElement connectionEmailInput;

    @FindBy(xpath = "//form[@id='pay-connection']//button[contains(text(), 'Продолжить')]")
    private WebElement connectionContinueButton;

    public MtsHomePage() {
        PageFactory.initElements(driver, this);
    }

    public MtsHomePage open() {
        driver.get(Constants.MTS_HOME_URL);
        return this;
    }

    public MtsHomePage acceptCookiesIfVisible() {
        List<WebElement> cookies = driver.findElements(By.id("cookie-agree"));
        if (!cookies.isEmpty() && cookies.get(0).isDisplayed()) {
            cookies.get(0).click();
            return this;
        }
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))).click();
        } catch (TimeoutException ignored) {
        }
        return this;
    }

    public MtsHomePage scrollToPayBlock() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("connection-phone")));
        scrollTo(payBlock);
        openPaymentFormByVariant(Constants.CONNECTION_VARIANT);
        return this;
    }

    public String getPayBlockTitleText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//section[contains(@class, 'pay')]//h2")
        ));
        return payBlockTitle.getText().replace("\n", " ").trim();
    }

    public List<WebElement> getPartnerLogos() {
        By logosLocator = By.xpath("//div[contains(@class, 'pay__partners')]//img");
        wait.until(driver -> driver.findElements(logosLocator).size() == 5);
        List<WebElement> logos = driver.findElements(logosLocator);
        scrollTo(logos.get(0));
        return logos;
    }

    public String getDetailsLinkText() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsAboutServiceLink));
        scrollTo(detailsAboutServiceLink);
        return detailsAboutServiceLink.getText().trim();
    }

    public String getDetailsLinkHref() {
        return detailsAboutServiceLink.getAttribute("href");
    }

    public MtsHomePage clickDetailsLink() {
        detailsAboutServiceLink.click();
        return this;
    }

    public MtsHomePage waitHelpPageOpened(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
        return this;
    }

    public MtsHomePage goBackToHome() {
        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("connection-phone")));
        openPaymentFormByVariant(Constants.CONNECTION_VARIANT);
        return this;
    }

    public MtsHomePage selectPaymentVariant(String variantName) {
        scrollTo(payBlock);
        openPaymentFormByVariant(variantName);
        return this;
    }

    public List<WebElement> getEmptyFieldInputsForVariant(String variantName) {
        return getFormInputsForVariant(variantName);
    }

    public List<String> getPlaceholdersForVariant(String variantName) {
        List<String> placeholders = new ArrayList<>();
        for (WebElement input : getFormInputsForVariant(variantName)) {
            placeholders.add(input.getAttribute("placeholder"));
        }
        return placeholders;
    }

    public boolean areAllFieldsEmpty(String variantName) {
        for (WebElement input : getFormInputsForVariant(variantName)) {
            if (!isFieldEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPlaceholderLabelVisible(WebElement input) {
        return isFieldEmpty(input);
    }

    public MtsHomePage clearFieldsForVariant(String variantName) {
        for (WebElement input : getFormInputsForVariant(variantName)) {
            input.clear();
        }
        return this;
    }

    public MtsHomePage typeIntoField(WebElement input, String value) {
        input.clear();
        input.sendKeys(value);
        return this;
    }

    private List<WebElement> getFormInputsForVariant(String variantName) {
        String formId = Constants.FORM_ID_BY_VARIANT.get(variantName);
        WebElement form = driver.findElement(By.id(formId));
        return form.findElements(By.cssSelector("input[placeholder]"));
    }

    private boolean isFieldEmpty(WebElement input) {
        String value = input.getAttribute("value");
        return value == null || value.isBlank();
    }

    public MtsHomePage fillConnectionServiceForm(String phone, String sum, String email) {
        openPaymentFormByVariant(Constants.CONNECTION_VARIANT);
        scrollTo(connectionForm);
        connectionPhoneInput.clear();
        connectionPhoneInput.sendKeys(phone);

        connectionSumInput.clear();
        connectionSumInput.sendKeys(sum);

        connectionEmailInput.clear();
        connectionEmailInput.sendKeys(email);
        return this;
    }

    public MtsHomePage clickConnectionContinue() {
        scrollTo(connectionContinueButton);
        shortWait.until(ExpectedConditions.elementToBeClickable(connectionContinueButton)).click();
        return this;
    }

    public WebElement waitPaymentIframe() {
        By iframeLocator = By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'checkout')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        return driver.findElement(iframeLocator);
    }

    private void openPaymentFormByVariant(String variantName) {
        new Select(paymentTypeSelect).selectByVisibleText(variantName);
        String formId = Constants.FORM_ID_BY_VARIANT.get(variantName);
        executeJs(
                "document.querySelectorAll('.pay-form').forEach(function(f) { f.classList.remove('opened'); });"
                        + "document.getElementById(arguments[0]).classList.add('opened');",
                formId
        );
    }
}
