public class lesson_2_task_8 {
    public static void main(String[] args) {
        printLineMultipleTimes("Aston AQA", 3);
    }
    public static void printLineMultipleTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }
}