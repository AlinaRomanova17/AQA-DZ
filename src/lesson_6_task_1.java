import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class lesson_6_task_1 {

    public static void removeStudentsAverageBelowThree(Collection<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void promoteToNextCourseIfAverageAtLeastThree(Collection<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) {
        List<Student> studentCollection = new ArrayList<>();
        studentCollection.add(new Student("Иван Петров", "ИС-101", 2,
                grades("Математика", 4, "Java", 5, "История", 2)));
        studentCollection.add(new Student("Ольга Сидорова", "ИС-102", 1,
                grades("Математика", 2, "Английский", 2)));
        studentCollection.add(new Student("Елена Козлова", "ИС-101", 2,
                grades("Математика", 5, "Java", 4)));
        studentCollection.add(new Student("Дмитрий Смирнов", "ИС-103", 1,
                grades("Математика", 3, "Физика", 3)));

        System.out.println("Студенты 2 курса:");
        printStudents(new HashSet<>(studentCollection), 2);

        removeStudentsAverageBelowThree(studentCollection);

        System.out.println("\nПосле удаления студентов со средней оценкой < 3:");
        for (Student s : studentCollection) {
            System.out.println(s.getName());
        }

        promoteToNextCourseIfAverageAtLeastThree(studentCollection);

        System.out.println("\nСтуденты 3 курса после перевода на следующий курс:");
        printStudents(new HashSet<>(studentCollection), 3);
    }

    private static Map<String, Integer> grades(Object... pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("нужно чётное число аргументов: предмет, оценка, ...");
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            map.put((String) pairs[i], (Integer) pairs[i + 1]);
        }
        return map;
    }
}

class Student {

    private final String name;
    private final String group;
    private int course;
    private final Map<String, Integer> subjectGrades;

    public Student(String name, String group, int course, Map<String, Integer> subjectGrades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.subjectGrades = new HashMap<>(subjectGrades);
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Map<String, Integer> getSubjectGrades() {
        return Collections.unmodifiableMap(subjectGrades);
    }

    public double getAverageGrade() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (int grade : subjectGrades.values()) {
            sum += grade;
        }
        return sum / subjectGrades.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }
}
