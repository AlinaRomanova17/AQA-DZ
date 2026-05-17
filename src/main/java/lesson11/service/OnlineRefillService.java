package lesson11.service;

import lesson11.page.BepaidWidgetPage;
import lesson11.page.MtsHomePage;
import io.qameta.allure.Step;
import lesson11.utils.Asserts;
import lesson11.utils.Constants;
import lesson11.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OnlineRefillService {

    private final MtsHomePage homePage = new MtsHomePage();
    private final BepaidWidgetPage bepaidPage = new BepaidWidgetPage();
    private final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    @Step("Открыть главную, принять cookies и прокрутить к блоку оплаты")
    public OnlineRefillService openHomeAcceptCookiesAndScrollToPay() {
        homePage.open()
                .acceptCookiesIfVisible()
                .scrollToPayBlock();
        return this;
    }

    @Step("Проверить блок «Онлайн-пополнение без комиссии» (урок 9)")
    public OnlineRefillService checkLesson9PayBlock() {
        Asserts.assertEquals(Constants.PAY_BLOCK_TITLE, homePage.getPayBlockTitleText());

        List<WebElement> logos = homePage.getPartnerLogos();
        Asserts.assertEquals(5, logos.size());
        for (String alt : Constants.EXPECTED_PARTNER_LOGO_ALTS) {
            Asserts.assertTrue(hasLogoWithAlt(logos, alt), "Логотип не найден: " + alt);
        }

        Asserts.assertEquals(Constants.DETAILS_LINK_TEXT, homePage.getDetailsLinkText());
        Asserts.assertTrue(homePage.getDetailsLinkHref().contains(Constants.DETAILS_LINK_URL_PART),
                "Ссылка «Подробнее» ведёт не на страницу помощи");

        homePage.clickDetailsLink()
                .waitHelpPageOpened(Constants.DETAILS_LINK_URL_PART);
        homePage.goBackToHome().scrollToPayBlock();

        return this;
    }

    @Step("Проверить плейсхолдеры для всех вариантов оплаты")
    public OnlineRefillService checkPlaceholdersForAllVariants() {
        for (String variant : Constants.PAYMENT_VARIANTS) {
            checkPlaceholdersForVariant(variant);
        }
        return this;
    }

    @Step("Проверить плейсхолдеры для варианта: {variantName}")
    public OnlineRefillService checkPlaceholdersForVariant(String variantName) {
        homePage.selectPaymentVariant(variantName)
                .clearFieldsForVariant(variantName);

        Asserts.assertTrue(homePage.areAllFieldsEmpty(variantName),
                "Поля должны быть пустыми перед проверкой: " + variantName);

        List<String> actualPlaceholders = homePage.getPlaceholdersForVariant(variantName);
        List<String> expectedPlaceholders = Constants.EXPECTED_PLACEHOLDERS_BY_VARIANT.get(variantName);
        Asserts.assertEquals(expectedPlaceholders, actualPlaceholders,
                "Неверные надписи в пустых полях для варианта: " + variantName);

        for (WebElement input : homePage.getEmptyFieldInputsForVariant(variantName)) {
            String placeholder = input.getAttribute("placeholder");
            Asserts.assertTrue(homePage.isPlaceholderLabelVisible(input),
                    "Надпись должна быть видна в пустом поле: " + placeholder);

            homePage.typeIntoField(input, "1");
            Asserts.assertFalse(homePage.isPlaceholderLabelVisible(input),
                    "Надпись должна исчезнуть после ввода в поле: " + placeholder);

            input.clear();
        }

        return this;
    }

    @Step("Заполнить форму «Связь» и нажать «Продолжить»")
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

    @Step("Проверить виджет оплаты bePaid")
    public OnlineRefillService checkBepaidPaymentWidget() {
        WebElement iframe = homePage.waitPaymentIframe();
        try {
            bepaidPage.switchToPaymentFrame(iframe);

            wait.until(driver -> bepaidPage.isPhoneShown(Constants.CONNECTION_PHONE));

            Asserts.assertTrue(bepaidPage.isPhoneShown(Constants.CONNECTION_PHONE),
                    "Телефон не отображается в окне оплаты");
            Asserts.assertTrue(bepaidPage.isAmountShown(Constants.CONNECTION_SUM),
                    "Сумма не отображается в окне оплаты");
            Asserts.assertTrue(bepaidPage.isAmountOnPayButton(Constants.CONNECTION_SUM),
                    "Сумма не отображается на кнопке оплаты");
            Asserts.assertTrue(bepaidPage.hasCardFieldHints(Constants.EXPECTED_CARD_FIELD_HINTS),
                    "Нет подсказок в полях карты");
            Asserts.assertFalse(bepaidPage.getCardFieldPlaceholders().isEmpty(),
                    "Нет placeholder в незаполненных полях карты");
            Asserts.assertTrue(bepaidPage.getPaymentImagesCount() > 0,
                    "Нет иконок платёжных систем");
            Asserts.assertFalse(bepaidPage.getCardInputs().isEmpty(),
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
