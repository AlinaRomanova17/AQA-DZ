package lesson10.utils;

import java.util.List;
import java.util.Map;

public final class Constants {

    public static final String MTS_HOME_URL = "https://www.mts.by/";
    public static final String PAY_BLOCK_TITLE = "Онлайн пополнение без комиссии";
    public static final String DETAILS_LINK_TEXT = "Подробнее о сервисе";
    public static final String DETAILS_LINK_URL_PART = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    public static final String CONNECTION_VARIANT = "Услуги связи";

    public static final String CONNECTION_PHONE = "297777777";
    public static final String CONNECTION_SUM = "102";
    public static final String CONNECTION_EMAIL = "alinkafm@gmail.com";

    public static final List<String> PAYMENT_VARIANTS = List.of(
            CONNECTION_VARIANT,
            "Домашний интернет",
            "Рассрочка",
            "Задолженность"
    );

    public static final Map<String, String> FORM_ID_BY_VARIANT = Map.of(
            CONNECTION_VARIANT, "pay-connection",
            "Домашний интернет", "pay-internet",
            "Рассрочка", "pay-instalment",
            "Задолженность", "pay-arrears"
    );

    public static final Map<String, String> FIRST_INPUT_ID_BY_VARIANT = Map.of(
            CONNECTION_VARIANT, "connection-phone",
            "Домашний интернет", "internet-phone",
            "Рассрочка", "score-instalment",
            "Задолженность", "score-arrears"
    );

    public static final List<String> EXPECTED_PARTNER_LOGO_ALTS = List.of(
            "Visa",
            "Verified By Visa",
            "MasterCard",
            "MasterCard Secure Code",
            "Белкарт"
    );

    public static final Map<String, List<String>> EXPECTED_PLACEHOLDERS_BY_VARIANT = Map.of(
            CONNECTION_VARIANT, List.of("Номер телефона", "Сумма", "E-mail для отправки чека"),
            "Домашний интернет", List.of("Номер абонента", "Сумма", "E-mail для отправки чека"),
            "Рассрочка", List.of("Номер счета на 44", "Сумма", "E-mail для отправки чека"),
            "Задолженность", List.of("Номер счета на 2073", "Сумма", "E-mail для отправки чека")
    );

    public static final List<String> EXPECTED_CARD_FIELD_HINTS = List.of("mm", "yy", "cvc", "карт");

    private Constants() {
    }
}
