import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class lesson_7_task_3_Test {

    @Test
    void add_subtract_multiply_plain() {
        assertEquals(13, lesson_7_task_3.add(10, 3));
        assertEquals(7, lesson_7_task_3.subtract(10, 3));
        assertEquals(42, lesson_7_task_3.multiply(6, 7));
        assertEquals(4, lesson_7_task_3.divide(20, 5));
    }

    @Test
    void divide_byZeroThrows() {
        assertThrows(ArithmeticException.class, () -> lesson_7_task_3.divide(5, 0));
    }

    @Test
    void divide_negativeOperands() {
        assertEquals(-3, lesson_7_task_3.divide(-9, 3));
        assertEquals(-3, lesson_7_task_3.divide(9, -3));
    }

    @ParameterizedTest
    @CsvSource({"-2, -3, -5", "0, 100, 100", "7, -2, 5"})
    void add_csv(int a, int b, int sum) {
        assertEquals(sum, lesson_7_task_3.add(a, b));
    }
}
