package lesson10.service;

import lesson10.page.BepaidWidgetPage;
import lesson10.page.MtsHomePage;
import lesson10.utils.Constants;
import lesson10.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlineRefillService {

    private final MtsHomePage homePage = new MtsHomePage();
    private final BepaidWidgetPage bepaidPage = new BepaidWidgetPage();
    private final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    public OnlineRefillService openHomeAcceptCookiesAndScrollToPay() {
        homePage.open()
                .acceptCookiesIfVisible()
                .scrollToPayBlock();
        return this;
    }

    public OnlineRefillService checkLesson9PayBlock() {
        assertEquals(Constants.PAY_BLOCK_TITLE, homePage.getPayBlockTitleText());

        List<WebElement> logos = homePage.getPartnerLogos();
        assertEquals(5, logos.size());
        for (String alt : Constants.EXPECTED_PARTNER_LOGO_ALTS) {
            assertTrue(hasLogoWithAlt(logos, alt), "Логотип не найден: " + alt);
        }

        assertEquals(Constants.DETAILS_LINK_TEXT, homePage.getDetailsLinkText());
        assertTrue(homePage.getDetailsLinkHref().contains(Constants.DETAILS_LINK_URL_PART));

        homePage.clickDetailsLink()
                .waitHelpPageOpened(Constants.DETAILS_LINK_URL_PART);
        homePage.goBackToHome().scrollToPayBlock();

        return this;
    }

    public OnlineRefillService checkPlaceholdersForAllVariants() {
        for (String variant : Constants.PAYMENT_VARIANTS) {
            checkPlaceholdersForVariant(variant);
        }
        return this;
    }

    public OnlineRefillService checkPlaceholdersForVariant(String variantName) {
        homePage.selectPaymentVariant(variantName)
                .clearFieldsForVariant(variantName);

        assertTrue(homePage.areAllFieldsEmpty(variantName),
                "Поля должны быть пустыми перед проверкой: " + variantName);

        List<String> actualPlaceholders = homePage.getPlaceholdersForVariant(variantName);
        List<String> expectedPlaceholders = Constants.EXPECTED_PLACEHOLDERS_BY_VARIANT.get(variantName);
        assertEquals(expectedPlaceholders, actualPlaceholders,
                "Неверные надписи в пустых полях для варианта: " + variantName);

        for (WebElement input : homePage.getEmptyFieldInputsForVariant(variantName)) {
            String placeholder = input.getAttribute("placeholder");
            assertTrue(homePage.isPlaceholderLabelVisible(input),
                    "Надпись должна быть видна в пустом поле: " + placeholder);

            homePage.typeIntoField(input, "1");
            assertFalse(homePage.isPlaceholderLabelVisible(input),
                    "Надпись должна исчезнуть после ввода в поле: " + placeholder);

            input.clear();
        }

        return this;
    }

    public OnlineRefillService fillConnectionFormAndContinue() {
        homePage.selectPaymentVariant(Constants.CONNECTION_VARIANT)
                .fillConnectionServiceForm(
                        Constants.CONNECTION_PHONE,
                        Constants.CONNECTION_SUM,
                        Constants.CONNECTION_EMAIL
                )
                .clickConnectionContinue();
        return this;
    }

    public OnlineRefillService checkBepaidPaymentWidget() {
        WebElement iframe = homePage.waitPaymentIframe();
        try {
            bepaidPage.switchToPaymentFrame(iframe);

            wait.until(driver -> bepaidPage.isPhoneShown(Constants.CONNECTION_PHONE));

            assertTrue(bepaidPage.isPhoneShown(Constants.CONNECTION_PHONE),
                    "Телефон не отображается в окне оплаты");
            assertTrue(bepaidPage.isAmountShown(Constants.CONNECTION_SUM),
                    "Сумма не отображается в окне оплаты");
            assertTrue(bepaidPage.isAmountOnPayButton(Constants.CONNECTION_SUM),
                    "Сумма не отображается на кнопке оплаты");
            assertTrue(bepaidPage.hasCardFieldHints(Constants.EXPECTED_CARD_FIELD_HINTS),
                    "Нет подсказок в полях карты");
            assertFalse(bepaidPage.getCardFieldPlaceholders().isEmpty(),
                    "Нет placeholder в незаполненных полях карты");
            assertTrue(bepaidPage.getPaymentImagesCount() > 0,
                    "Нет иконок платёжных систем");
            assertFalse(bepaidPage.getCardInputs().isEmpty(),
                    "Нет полей ввода реквизитов карты");
        } finally {
            bepaidPage.switchToMainPage();
        }
        return this;
    }

    private boolean hasLogoWithAlt(List<WebElement> logos, String altText) {
        for (WebElement logo : logos) {
            if (altText.equals(logo.getAttribute("alt"))) {
                return true;
            }
        }
        return false;
    }
}
