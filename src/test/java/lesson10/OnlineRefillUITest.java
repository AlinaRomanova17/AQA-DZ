package lesson10;

import org.junit.jupiter.api.Test;

public class OnlineRefillUITest extends BaseTest {

    @Test
    void onlineRefillWithoutCommission_fullScenario() {
        refillService
                .openHomeAcceptCookiesAndScrollToPay()
                .checkLesson9PayBlock()
                .checkPlaceholdersForAllVariants()
                .fillConnectionFormAndContinue()
                .checkBepaidPaymentWidget();
    }
}
