package practical.practical1;

import seminar.lesson1.Father;
import seminar.lesson1.Human;

public class Main {
    public static void main(String[] args) {
        Human myHuman = new Father("Вася", "м", true);
        System.out.println(myHuman.getSex());
    }
}