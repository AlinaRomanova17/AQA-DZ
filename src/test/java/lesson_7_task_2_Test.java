import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class lesson_7_task_2_Test {

    @Test
    void triangleArea_right345() {
        double actual = lesson_7_task_2.triangleArea(3, 4, 5);
        assertEquals(6.0, actual, 1e-9);
    }

    @Test
    void triangleArea_equilateral() {
        double a = lesson_7_task_2.triangleArea(2, 2, 2);
        assertTrue(a > 0);
        assertEquals(Math.sqrt(3), a, 1e-9);
    }

    @Test
    void triangleArea_degenerateRejected() {
        assertThrows(IllegalArgumentException.class, () -> lesson_7_task_2.triangleArea(1, 2, 10));
    }

    @Test
    void triangleArea_nonPositiveSideRejected() {
        assertThrows(IllegalArgumentException.class, () -> lesson_7_task_2.triangleArea(3, 4, -1));
    }
}
