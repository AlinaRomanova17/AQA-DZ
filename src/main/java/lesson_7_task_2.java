/** Лекция 2.7: площадь треугольника по трём сторонам (формула Герона). */
public class lesson_7_task_2 {

    /**
     * Все три стороны должны быть положительными и удовлетворять неравенству треугольника.
     */
    public static double triangleArea(double a, double b, double c) {
        if (!isPositive(a) || !isPositive(b) || !isPositive(c)) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Не выполняется неравенство треугольника");
        }
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    private static boolean isPositive(double x) {
        return Double.isFinite(x) && x > 0;
    }

    public static void main(String[] args) {
        System.out.println("Треугольник 3, 4, 5 — площадь " + triangleArea(3, 4, 5));
    }
}
