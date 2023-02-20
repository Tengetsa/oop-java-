package practical.practical6.view;

import practical.practical6.model.Student;
import practical.practical6.model.Teacher;
import practical.practical6.model.User;
import practical.practical6.service.UserService;
import practical.practical6.impls.UserServiceImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserView extends AbstractTableModel {

    private UserService userService = new UserServiceImpl();
    private List<User> users;

    public UserView(List<User> users) {
        this.users = users;
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return users.size();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return false;
        return true;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Категория";
            case 1:
                return "Имя";
            case 2:
                return "Средний балл";
            case 3:
                return "Класс";
            case 4:
                return "Учитель";
        }
        return "";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        if (user instanceof Student) {
            switch (columnIndex) {
                case 0 -> {
                    return user.getClass().getSimpleName();
                }
                case 1 -> {
                    return user.getName();
                }
                case 2 -> {
                    return ((Student) user).getGrade();
                }
                case 3 -> {
                    return ((Student) user).getYear();
                }
                case 4 -> {
                    return ((Student) user).getTeacher().getName();
                }
            }
        } else if (user instanceof Teacher) {
            switch (columnIndex) {
                case 0 -> {
                    return user.getClass().getSimpleName();
                }
                case 1 -> {
                    return user.getName();
                }
            }
        }
        return "";
    }

    public void setValueAt(Object value, int row, int col) {
        User user = users.get(row);
        if (user instanceof Student) {
            switch (col) {
                case 1 -> {
                    user.setName(String.valueOf(value));

                }
                case 2 -> {
                    try {
                        float fValue = Float.parseFloat(String.valueOf(value));
                        if (fValue >= 0.0 && fValue <= 5.0)
                            ((Student) user).setGrade(Float.parseFloat(String.valueOf(value)));
                    } catch (Exception ignored) {
                    }
                }
                case 3 -> {
                    try {
                        int iValue = Integer.parseInt(String.valueOf(value));
                        if (List.of(1, 2, 3, 4, 5, 6).contains(iValue))
                            ((Student) user).setYear(Integer.parseInt((String.valueOf(value))));
                    } catch (Exception ignored) {
                    }
                }
                case 4 -> {
                    ((Student) user).getTeacher().setName(String.valueOf(value));
                }
                // ...
            }
        } else if (user instanceof Teacher) {
            switch (col) {

                case 1 -> {
                    user.setName(String.valueOf(value));
                }
            }
        }
        fireTableDataChanged();
    }
}