package lesson11;

import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import lesson11.service.OnlineRefillService;
import lesson11.utils.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    protected OnlineRefillService refillService;

    @RegisterExtension
    private final TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        public void testFailed(org.junit.jupiter.api.extension.ExtensionContext context, Throwable cause) {
            attachScreenshot("Скриншот при падении теста");
        }
    };

    @BeforeEach
    void setUp() {
        refillService = new OnlineRefillService();
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }

    @AfterAll
    static void tearDownAll() {
        DriverManager.quitDriver();
    }

    private static void attachScreenshot(String name) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment(name, "image/png", "png", screenshot);
            }
        } catch (Exception ignored) {
        }
    }
}
