package Day02;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StudentManager MANAGER = new StudentManager();

    public static void main(String[] args) {
        System.out.println("欢迎进入学生管理系统");
        while (true) {
            printMenu();
            switch (readInt("请选择功能：")) {
                case 1 -> addStudent();
                case 2 -> showStudents(MANAGER.getStudentsById());
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> updateStudent();
                case 6 -> showStudents(MANAGER.getStudentsByAge());
                case 7 -> showStatistics();
                case 0 -> { System.out.println("感谢使用，再见！"); return; }
                default -> System.out.println("无效选择，请重新输入。");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========== 学生管理系统 ==========");
        System.out.println("1. 添加学生");
        System.out.println("2. 查看全部学生（按学号排序）");
        System.out.println("3. 查询学生");
        System.out.println("4. 删除学生");
        System.out.println("5. 修改学生信息");
        System.out.println("6. 按年龄排序查看");
        System.out.println("7. 查看统计信息");
        System.out.println("0. 退出系统");
    }

    private static void addStudent() {
        String id = readRequired("请输入学号：");
        if (MANAGER.findById(id) != null) {
            System.out.println("该学号已存在，不能重复录入。");
            return;
        }
        try {
            Student student = new Student(readRequired("请输入姓名："), readAge(), id);
            MANAGER.addStudent(student);
            System.out.println("录入成功。");
        } catch (IllegalArgumentException e) {
            System.out.println("录入失败：" + e.getMessage());
        }
    }

    private static void searchStudent() {
        System.out.println("1. 按学号精确查询\n2. 按姓名关键字查询");
        int choice = readInt("请选择查询方式：");
        if (choice == 1) {
            Student student = MANAGER.findById(readRequired("请输入学号："));
            if (student == null) System.out.println("未找到该学生。");
            else showStudents(List.of(student));
        } else if (choice == 2) {
            showStudents(MANAGER.findByName(readRequired("请输入姓名关键字：")));
        } else {
            System.out.println("无效选择。");
        }
    }

    private static void deleteStudent() {
        String id = readRequired("请输入要删除的学生学号：");
        System.out.println(MANAGER.deleteStudent(id) ? "删除成功。" : "未找到该学生。");
    }

    private static void updateStudent() {
        String id = readRequired("请输入要修改的学生学号：");
        if (MANAGER.findById(id) == null) {
            System.out.println("未找到该学生。");
            return;
        }
        try {
            MANAGER.updateStudent(id, readRequired("请输入新姓名："), readAge());
            System.out.println("修改成功。");
        } catch (IllegalArgumentException e) {
            System.out.println("修改失败：" + e.getMessage());
        }
    }

    private static void showStatistics() {
        System.out.printf("当前学生总数：%d 人，平均年龄：%.1f 岁%n", MANAGER.getCount(), MANAGER.getAverageAge());
    }

    private static void showStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("暂无学生数据。");
            return;
        }
        System.out.printf("%-12s %-12s %-6s%n", "学号", "姓名", "年龄");
        System.out.println("-----------------------------------");
        for (Student student : students) {
            System.out.printf("%-12s %-12s %-6d%n", student.getId(), student.getName(), student.getAge());
        }
    }

    private static String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = SCANNER.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.println("输入不能为空，请重试。");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入整数。");
            }
        }
    }

    private static int readAge() {
        while (true) {
            int age = readInt("请输入年龄：");
            if (age >= 1 && age <= 150) return age;
            System.out.println("年龄应在 1 到 150 之间。");
        }
    }
}
