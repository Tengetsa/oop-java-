package practical.practical4.model;

import java.util.List;
import java.util.Objects;


public class Teacher extends User {
    private List<Student> group;

    public Teacher(String name) {
        this.setName(name);
    }

    public List<Student> getGroup() {
        return group;
    }

    public void setGroup(List<Student> group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(this.getName(), teacher.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return "_Учитель_"
                + this.getName() +
                "\n";
    }
}
