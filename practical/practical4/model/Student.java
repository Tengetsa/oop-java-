package practical.practical4.model;

import java.util.Objects;

public class Student extends User {

    private float grade;
    private int year;

    public Student(String name, float grade, int year, Teacher teacher) {
        super();
        this.setName(name);
        this.grade = grade;
        this.year = year;
        this.teacher = teacher;
    }

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.grade, grade) == 0 && year
                == student.year && Objects.equals(this.getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), grade, year);
    }

    @Override
    public String toString() {
        return "_Ученик_" +
                "Имя_" + this.getName() +
                "_Средний балл_" + this.grade +
                "_Класс_" + this.year +
                "_Учитель_" + teacher.getName() +
                "\n";
    }
}