package Day02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** 负责学生数据的增、删、查、改和统计。 */
public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    /** 新增学生；学号重复时返回 false。 */
    public boolean addStudent(Student student) {
        if (findById(student.getId()) != null) return false;
        students.add(student);
        return true;
    }

    public Student findById(String id) {
        if (id == null) return null;
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id.trim())) return student;
        }
        return null;
    }

    /** 支持按姓名关键字模糊查询。 */
    public List<Student> findByName(String keyword) {
        List<Student> result = new ArrayList<>();
        if (keyword == null) return result;
        String text = keyword.trim().toLowerCase();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(text)) result.add(student);
        }
        return result;
    }

    public boolean deleteStudent(String id) {
        Student student = findById(id);
        if (student == null) return false;
        students.remove(student);
        return true;
    }

    public boolean updateStudent(String id, String name, int age) {
        Student student = findById(id);
        if (student == null) return false;
        student.setName(name);
        student.setAge(age);
        return true;
    }

    public List<Student> getStudentsById() {
        List<Student> result = new ArrayList<>(students);
        result.sort(Comparator.comparing(Student::getId));
        return result;
    }

    public List<Student> getStudentsByAge() {
        List<Student> result = new ArrayList<>(students);
        result.sort(Comparator.comparingInt(Student::getAge));
        return result;
    }

    public int getCount() { return students.size(); }

    public double getAverageAge() {
        if (students.isEmpty()) return 0;
        int total = 0;
        for (Student student : students) total += student.getAge();
        return (double) total / students.size();
    }
}
