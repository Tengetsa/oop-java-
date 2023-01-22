package practical.practical2;

import java.util.HashMap;
import java.util.Map;

// касс объевляем как final( чтобы от него нельзя было наследовать)
public final class Immutable {
    // создаём приватные поля

    private final String month;
    private final int day;
    private final Map<String, Integer> mapBirthday;

    // создаём параметризованный конструктор
    public Immutable(String str, int day, Map<String, Integer> mapBirthday){
        this.month = str;
        this.day = day;

        // Для полей-коллекций необходимо делать глубокие копии
        Map<String, Integer> deepCopy = new HashMap<>();
        for (String key: mapBirthday.keySet()) {
            deepCopy.put(key, mapBirthday.get(key));
        }
        this.mapBirthday = deepCopy;
    }

    // в классе не должно быть сеттеров(set)
    public String getStr() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Map<String, Integer> getMapBirthday() {

        // Для полей-коллекций необходимо делать глубокие копии
        Map<String, Integer> deepCopy = new HashMap<>();
        for (String key: mapBirthday.keySet()) {
            deepCopy.put(key, mapBirthday.get(key));
        }
        return deepCopy;
    }
}
