import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class lesson_7_task_3_Test {

    @Test
    public void add_subtract_multiply_plain() {
        Assert.assertEquals(lesson_7_task_3.add(10, 3), 13);
        Assert.assertEquals(lesson_7_task_3.subtract(10, 3), 7);
        Assert.assertEquals(lesson_7_task_3.multiply(6, 7), 42);
        Assert.assertEquals(lesson_7_task_3.divide(20, 5), 4);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divide_byZeroThrows() {
        lesson_7_task_3.divide(5, 0);
    }

    @Test
    public void divide_negativeOperands() {
        Assert.assertEquals(lesson_7_task_3.divide(-9, 3), -3);
        Assert.assertEquals(lesson_7_task_3.divide(9, -3), -3);
    }

    @DataProvider(name = "sums")
    public Object[][] sums() {
        return new Object[][]{{-2, -3, -5}, {0, 100, 100}, {7, -2, 5}};
    }

    @Test(dataProvider = "sums")
    public void add_dataProvider(int a, int b, int sum) {
        Assert.assertEquals(lesson_7_task_3.add(a, b), sum);
    }
}
