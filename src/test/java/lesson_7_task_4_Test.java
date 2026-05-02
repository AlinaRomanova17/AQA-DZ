import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class lesson_7_task_4_Test {

    @Test
    void compareInts_relativeOrder() {
        assertTrue(lesson_7_task_4.compareInts(1, 2) < 0);
        assertEquals(0, lesson_7_task_4.compareInts(4, 4));
        assertTrue(lesson_7_task_4.compareInts(10, 3) > 0);
    }

    @ParameterizedTest
    @CsvSource({"-5, -5, 0", "-1, 8, -1", "42, -1, 1"})
    void compareInts_pairs(int a, int b, int expectedSignum) {
        int c = lesson_7_task_4.compareInts(a, b);
        assertEquals(expectedSignum, Integer.signum(c));
    }
}
