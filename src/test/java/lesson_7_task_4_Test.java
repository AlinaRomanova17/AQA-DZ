import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class lesson_7_task_4_Test {

    @Test
    public void compareInts_relativeOrder() {
        Assert.assertTrue(lesson_7_task_4.compareInts(1, 2) < 0);
        Assert.assertEquals(lesson_7_task_4.compareInts(4, 4), 0);
        Assert.assertTrue(lesson_7_task_4.compareInts(10, 3) > 0);
    }

    @DataProvider(name = "pairs")
    public Object[][] pairs() {
        return new Object[][]{{-5, -5, 0}, {-1, 8, -1}, {42, -1, 1}};
    }

    @Test(dataProvider = "pairs")
    public void compareInts_dataProvider(int a, int b, int expectedSignum) {
        int c = lesson_7_task_4.compareInts(a, b);
        Assert.assertEquals(Integer.signum(c), expectedSignum);
    }
}
