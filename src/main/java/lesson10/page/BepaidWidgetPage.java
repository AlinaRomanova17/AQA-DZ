package lesson10.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BepaidWidgetPage extends BasePage {

    private static final By VISIBLE_INPUTS = By.xpath(
            "//input[not(@type='hidden') and not(@type='submit')]"
    );
    private static final By PAYMENT_IMAGES = By.xpath("//img");

    @FindBy(tagName = "body")
    private WebElement body;

    public BepaidWidgetPage switchToPaymentFrame(WebElement iframe) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(body));
        return this;
    }

    public void switchToMainPage() {
        driver.switchTo().defaultContent();
    }

    public String getBodyText() {
        wait.until(ExpectedConditions.visibilityOf(body));
        return body.getText().replace("\n", " ");
    }

    public String getPageContent() {
        return (getBodyText() + " " + driver.getPageSource()).toLowerCase();
    }

    public boolean isPhoneShown(String phone) {
        String content = getPageContent();
        return content.contains(phone)
                || content.contains("+375" + phone)
                || content.contains("375" + phone)
                || content.contains(formatPhone(phone));
    }

    public boolean isAmountShown(String amount) {
        return getPageContent().contains(amount);
    }

    public boolean isAmountOnPayButton(String amount) {
        List<WebElement> buttons = driver.findElements(
                By.xpath("//button[contains(., '" + amount + "')]")
        );
        if (!buttons.isEmpty()) {
            return true;
        }
        return isAmountShown(amount);
    }

    public List<String> getCardFieldPlaceholders() {
        List<String> placeholders = new ArrayList<>();
        for (WebElement input : getCardInputs()) {
            String placeholder = input.getAttribute("placeholder");
            if (placeholder != null && !placeholder.isBlank()) {
                placeholders.add(placeholder.trim());
            }
        }
        return placeholders;
    }

    public boolean hasCardFieldHints(List<String> hints) {
        String pageContent = getPageContent();
        for (String hint : hints) {
            if (!containsAny(pageContent, hintVariants(hint))) {
                return false;
            }
        }
        return true;
    }

    public int getPaymentImagesCount() {
        return findInCurrentAndNestedFrames(PAYMENT_IMAGES).size();
    }

    public List<WebElement> getCardInputs() {
        return findInCurrentAndNestedFrames(VISIBLE_INPUTS);
    }

    private boolean containsAny(String content, String... variants) {
        for (String variant : variants) {
            if (content.contains(variant.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String[] hintVariants(String hint) {
        switch (hint.toLowerCase()) {
            case "mm":
            case "мм":
                return new String[]{"mm", "мм"};
            case "yy":
            case "гг":
                return new String[]{"yy", "гг", "год"};
            case "cvc":
                return new String[]{"cvc", "cvv"};
            default:
                return new String[]{hint};
        }
    }

    private List<WebElement> findInCurrentAndNestedFrames(By locator) {
        List<WebElement> elements = new ArrayList<>(driver.findElements(locator));
        if (!elements.isEmpty()) {
            return elements;
        }

        List<WebElement> nestedFrames = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : nestedFrames) {
            try {
                driver.switchTo().frame(frame);
                elements.addAll(driver.findElements(locator));
            } finally {
                driver.switchTo().parentFrame();
            }
        }
        return elements;
    }

    private String formatPhone(String phone) {
        if (phone.length() == 9) {
            return phone.substring(0, 2) + " " + phone.substring(2, 5) + " " + phone.substring(5);
        }
        return phone;
    }
}
