import org.testng.Assert;
import org.testng.annotations.Test;

public class lesson_7_task_2_Test {

    @Test
    public void triangleArea_right345() {
        double actual = lesson_7_task_2.triangleArea(3, 4, 5);
        Assert.assertEquals(actual, 6.0, 1e-9);
    }

    @Test
    public void triangleArea_equilateral() {
        double a = lesson_7_task_2.triangleArea(2, 2, 2);
        Assert.assertTrue(a > 0);
        Assert.assertEquals(a, Math.sqrt(3), 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void triangleArea_degenerateRejected() {
        lesson_7_task_2.triangleArea(1, 2, 10);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void triangleArea_nonPositiveSideRejected() {
        lesson_7_task_2.triangleArea(3, 4, -1);
    }
}
