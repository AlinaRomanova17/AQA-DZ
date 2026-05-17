package lesson11;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("МТС")
@Feature("Онлайн-пополнение без комиссии")
public class OnlineRefillUITest extends BaseTest {

    @Test
    @Story("Полный UI-сценарий")
    @DisplayName("Онлайн-пополнение без комиссии — полный сценарий")
    void onlineRefillWithoutCommission_fullScenario() {
        refillService
                .openHomeAcceptCookiesAndScrollToPay()
                .checkLesson9PayBlock()
                .checkPlaceholdersForAllVariants()
                .fillConnectionFormAndContinue()
                .checkBepaidPaymentWidget();
    }
}
