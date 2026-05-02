import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lesson_6_task_2 {

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "+375 (29) 111-22-33");
        phonebook.add("Иванов", "+375 (44) 555-66-77");
        phonebook.add("Петров", "+375 (33) 999-88-11");

        System.out.println("Телефоны Иванова: " + phonebook.get("Иванов"));
        System.out.println("Телефоны Петрова: " + phonebook.get("Петров"));
    }
}

class Phonebook {

    private final Map<String, List<String>> surnameToPhones = new HashMap<>();

    public void add(String surname, String phone) {
        surnameToPhones.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
    }

    public List<String> get(String surname) {
        if (surname == null || !surnameToPhones.containsKey(surname)) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<>(surnameToPhones.get(surname)));
    }
}
