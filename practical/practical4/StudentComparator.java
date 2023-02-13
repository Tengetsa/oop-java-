package practical.practical4;

import practical.practical4.model.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getYear(), o2.getYear());
    }
}
