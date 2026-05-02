import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class lesson_7_task_1_Test {

    @Test
    void factorial_knownValues() {
        assertEquals(1L, lesson_7_task_1.factorial(0));
        assertEquals(1L, lesson_7_task_1.factorial(1));
        assertEquals(120L, lesson_7_task_1.factorial(5));
    }

    @Test
    void factorial_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> lesson_7_task_1.factorial(-1));
    }
}
