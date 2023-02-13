package practical.practical4.controller;

import practical.practical4.model.Student;
import practical.practical4.model.Teacher;
import practical.practical4.model.User;
import practical.practical4.service.UserService;
import practical.practical4.impl.UserServiceImpl;

public class UserController<T extends User> {

    private UserService userService = new UserServiceImpl();
    public void addStudent(String name, float grade, int year, Teacher teacher){
        userService.add(new Student(name, grade, year, new Teacher(teacher.getName())));
    }

    public void addTeacher(String name) {
        userService.add(new Teacher(name));
    }

    public void remove(T user){
        userService.remove(user);
    }

    public User get(T user){
        return userService.get(user);
    }

    public void updateUser(T user, T updatedUser) {
        remove(user);
        userService.add(updatedUser);
    }
}
