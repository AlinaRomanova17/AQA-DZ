package lesson10;

import lesson10.service.OnlineRefillService;
import lesson10.utils.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected OnlineRefillService refillService;

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
}
