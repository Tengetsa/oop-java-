package practical.practical6.repository;

import practical.practical6.model.Student;
import practical.practical6.model.Teacher;
import practical.practical6.model.User;
import practical.practical6.service.UserService;
import practical.practical6.impls.UserServiceImpl;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserRepository<T extends User> {
    private List<T> users = new ArrayList<>();
    String outputFileName = "Users.txt";


    public void load() {
        users.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(outputFileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens;
                tokens = line.split("_");
                if ("Ученик".equals(tokens[1])) {
                    Student student = new Student(tokens[3], Float.parseFloat(tokens[5]),
                            Integer.parseInt(tokens[7]), new Teacher (tokens[9]));
                    users.add((T) student);
                } else if ("Учитель".equals(tokens[1])) {
                    Teacher teacher = new Teacher(tokens[2]);
                    users.add((T) teacher);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void save(T user){
        load();
        if (!users.contains(user)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))) {
                users.add(user);
                writer.write((users.size() - 1) + user.toString());
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            System.out.println("Пользователь уже существует!" + "\nУдалите текущий файл <Users.txt> и запустите программу!");
            System.exit(0);
        }
    }
    public void saveAll(List<T> users){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (int i = 0; i < users.size(); i++) {
                writer.write(i + users.get(i).toString());
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public List<T> getUsers() {
        load();
        Collections.sort(users, (o1, o2) -> o1.getClass().getName().
                compareTo(o2.getClass().getName()));
        return users;
    }

    public User getUser(T t) {
        load();
        if (users.contains(t)) {
            int index = users.indexOf(t);
            return users.get(index);
        }
        return null;
    }

    public boolean remove (T t) {
        load();
        if (users.contains(t)) {
            users.remove(t);
            saveAll(users);
            return true;
        }
        return false;
    }

    public boolean remove (int index) {
        load();
        if (index < users.size()) {
            users.remove(index);
            saveAll(users);
            return true;
        }
        return false;
    }
    public void sort(T user) {
        load();
        UserService userService = new UserServiceImpl();
        List<T> bestStudents = new ArrayList<>();
        for (User x : userService.getAllUsers()) {
            if (x != null) {
                T t = (T) x;
                bestStudents.add(t);
            }
        }
    }
}