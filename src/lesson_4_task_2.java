public class lesson_4_task_2 {
    public static void main(String[] args) {
        Figure[] figures = {new Circle(5, "Красный", "Черный"), new Rectangle(6, 4, "Синий", "Белый"), new Triangle(3, 4, 5, "Зеленый", "Серый")};
        for (Figure figure : figures) {
            figure.printInfo();
        }
    }
}

interface Figure {
    double getArea();
    double getPerimeter();
    String getFillColor();
    String getBorderColor();
    default void printInfo() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
        System.out.println("---------------");
    }
}

class Circle implements Figure {
    double radius;
    String fillColor;
    String borderColor;
    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getBorderColor() {
        return borderColor;
    }
}

class Rectangle implements Figure {
    double width;
    double height;
    String fillColor;
    String borderColor;
    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2 * (width + height);
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getBorderColor() {
        return borderColor;
    }
}

class Triangle implements Figure {
    double a;
    double b;
    double c;
    String fillColor;
    String borderColor;
    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double getPerimeter() {
        return a + b + c;
    }
    public String getFillColor() {
        return fillColor;
    }
    public String getBorderColor() {
        return borderColor;
    }
}
