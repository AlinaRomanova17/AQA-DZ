import org.testng.Assert;
import org.testng.annotations.Test;

public class lesson_7_task_1_Test {

    @Test
    public void factorial_knownValues() {
        Assert.assertEquals(lesson_7_task_1.factorial(0), 1L);
        Assert.assertEquals(lesson_7_task_1.factorial(1), 1L);
        Assert.assertEquals(lesson_7_task_1.factorial(5), 120L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void factorial_negative_throws() {
        lesson_7_task_1.factorial(-1);
    }
}
