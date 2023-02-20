package practical.practical6;

import practical.practical6.model.Student;
import practical.practical6.service.UserService;
import practical.practical6.impls.UserServiceImpl;

import java.util.Comparator;
import java.util.List;

public class StudentComparator implements Comparator<Student> {

    public List<Student> compareByGradeBest() {
        UserService userService = new UserServiceImpl();
        List<Student> bestStudents = userService.getAllUsers().stream()
                .filter(Student.class::isInstance)
                .map(x -> (Student) x)
                .filter(x-> x.getGrade() > 0.80 * 5)
                .toList();
        return bestStudents;
    }
    public List<Student> compareByGradeWorst() {
        UserService userService = new UserServiceImpl();
        List<Student> worstStudents = userService.getAllUsers().stream()
                .filter(Student.class::isInstance)
                .map(x -> (Student) x)
                .filter(x-> x.getGrade() < 0.45 * 5)
                .toList();
        return worstStudents;
    }
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getYear(), o2.getYear());
    }
}