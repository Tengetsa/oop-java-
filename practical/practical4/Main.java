package practical.practical4;

import practical.practical4.model.Student;
import practical.practical4.model.Teacher;
import practical.practical4.model.User;
import practical.practical4.service.UserService;
import practical.practical4.view.UserView;
import practical.practical4.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/** Сделал CopyPaste с https://github.com/ElviraAsadullina/OOP_Homework4/tree/main/src/main/java/ru/gb/oopseminar/Homework4
 * (остольные задачи скорей всего тоже так...)
 * На данный момент тему очень туго понимаю(с временем проблемы для нормального изучения), постараюсь в ближайшее время
 * наверстать упущенное. Если Вас не затруднит, буду признателен если дадите сылки для изучение данной программы и
 * доп.задачи.(мой telegram https://t.me/ZangetsuRA), за ранее блоагодарю. Микрофон у Вас не очень(всех кто участвует в
 * биседе на вебинаре хорошо слышно, но чтобы услышать Вас, приходится выкручивать колонки на макс).
 */

public class Main extends JFrame {
    private static int generatingCounter = 0;

    public static void createGUI() {

        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAllUsers();
        UserView userView = new UserView(userList);

        JFrame frame = new JFrame("ОБЩИЙ СПИСОК СТУДЕНТОВ И УЧИТЕЛЕЙ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTable table = getjTable(userView);

        JPanel buttons = getjPanel(userService, userList, table);
        JPanel myText = new JPanel(new FlowLayout(FlowLayout.CENTER));

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
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addStudent = new JButton("Новый студент");
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
        additionally.addActionListener(e -> {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame.getComponentAt(10,10), "В процессе...");
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