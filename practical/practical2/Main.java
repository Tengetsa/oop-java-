package practical.practical2;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> maps = new HashMap<>();
        maps.put("Сентябрь", 23);

        // Инициализация нашего "иммутабельного" класса
        // Immutable immutable = new Immutable("Май", 7, Map.of("Июнь", 6));
        Immutable immutable = new Immutable("Май", 7, maps);

        // Мы не можем изменять состояние объекта
        // через добавление элементов в полученную map
        System.out.println("Результат изменения map после того, как мы получим ее из объекта");
        immutable.getMapBirthday().put("Август", 7);
        immutable.getMapBirthday().keySet().forEach(System.out::println);
        immutable.getMapBirthday().values().forEach(System.out::println);

        System.out.println("Результат отображения объекта после изменения исходной map");
        maps.put("Февраль", 4);
        immutable.getMapBirthday().keySet().forEach(System.out::println);
        immutable.getMapBirthday().values().forEach(System.out::println);
    }
}
