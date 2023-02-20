package practical.practical6;

import practical.practical6.model.*;
import practical.practical6.service.*;
import practical.practical6.view.*;
import practical.practical6.impls.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main extends JFrame {
    private static int generatingCounter = 0;

    public static JFrame frame;

    public static void createGUI() {

        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAllUsers();
        UserView userView = new UserView(userList);

        frame = new JFrame("ОБЩИЙ СПИСОК СТУДЕНТОВ И УЧИТЕЛЕЙ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTable table = getjTable(userView);

        JPanel buttons = getjPanel(userService, userList, table);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(buttons, "South");

        frame.setPreferredSize(new Dimension(1100, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel getjPanel(UserService userService, List<User> userList, JTable table) {
        StudentComparator sc = new StudentComparator();
//        TeacherComparator tc = new TeacherComparator();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addStudent = new JButton("Новый ученик");
        addStudent.addActionListener(e -> {
            userService.add(new Student("< Ввод >" + generatingCounter++, 0.00f,0, new Teacher("Прокофьева Нина Петровна")));
            table.updateUI();
        });
        JButton addTeacher = new JButton("Новый учитель");
        addTeacher.addActionListener(e -> {
            userService.add(new Teacher("< Ввод >" + generatingCounter++));
            table.updateUI();
        });
        JButton removeTile = new JButton("Удалить");
        removeTile.addActionListener(e -> {
            int idx = table.getSelectedRow();
            userService.remove(idx);
            table.updateUI();
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame.getComponentAt(10,10), "Пользователь удален!");
        });
        JButton saveAll = new JButton("Сохранить");
        saveAll.addActionListener(e -> {
            userService.saveAll(userList);
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame.getComponentAt(10,10), "Данные сохранены!");
        });
        JButton additionally = new JButton("Дополнительно");
        JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Показать лучших учеников школы") {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("СПИСОК ЛУЧШИХ УЧЕНИКОВ ШКОЛЫ");
                userList.clear();
                List<Student> s = sc.compareByGradeBest();
                for(Student i: s) {
                    userList.add(i);
                }
                table.updateUI();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Показать учеников с плохой успеваемостью") {
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("СПИСОК УЧЕНИКОВ С ПЛОХОЙ УСПЕВАЕМОСТЬЮ");
                userList.clear();
                List<Student> s = sc.compareByGradeWorst();
                for(Student i: s) {
                    userList.add(i);
                }
                table.updateUI();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Посмотреть рейтинг учителей") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame.getComponentAt(10,10), "В процессе...");
            }
        }));

        additionally.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        buttons.add(addStudent);
        buttons.add(addTeacher);
        buttons.add(removeTile);
        buttons.add(saveAll);
        buttons.add(additionally);
        return buttons;
    }

    private static JTable getjTable(UserView userView) {
        JTable table = new JTable(userView);
        JTextField textField = new JTextField();
        textField.setFont(new Font("Serif", Font.PLAIN, 18));
        DefaultCellEditor dce = new DefaultCellEditor(textField);
        table.getColumnModel().getColumn(1).setCellEditor(dce);
        table.getColumnModel().getColumn(2).setCellEditor(dce);
        table.getColumnModel().getColumn(3).setCellEditor(dce);
        table.getColumnModel().getColumn(4).setCellEditor(dce);
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        table.setRowHeight(22);
        return table;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(false);
            createGUI();
        });
    }
}