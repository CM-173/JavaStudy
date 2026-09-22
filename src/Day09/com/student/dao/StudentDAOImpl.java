package Day09.com.student.dao;

import Day09.com.student.entity.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class StudentDAOImpl implements StudentDAO {

    private HashMap<String, Student> students = new HashMap<>();

    public StudentDAOImpl() {
        load();
    }

    //读取数据
    private void load() {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new FileReader("data/student.txt")
                    );
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length != 3) {
                    continue;
                }
                Student student =
                        new Student(
                                arr[0],
                                Integer.parseInt(arr[1]),
                                arr[2]
                        );
                students.put(student.getId(), student);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("文件读取失败");
        }
    }


    //添加
    @Override
    public boolean save(Student student) {
        if (students.containsKey(student.getId())) {
            return false;
        }
        students.put(student.getId(), student);
        saveFile();
        return true;
    }

    private void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter("data/student.txt");
            for (Student student : students.values()) {
                fileWriter.write(student.getName() + ","
                        + student.getAge() + ","
                        + student.getId() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("文件保存失败");
        }
    }

    //查询全部
    @Override
    public HashMap<String, Student> findAll() {
        return students;
    }

    //根据id查询
    @Override
    public Student findById(String id) {
        return students.get(id);
    }

    //删除
    @Override
    public boolean delete(String id) {
        Student student = students.remove(id);
        if (student != null){
            saveFile();
            return true;
        }
        return false;
    }

    //修改
    @Override
    public boolean update(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            saveFile();
            return true;
        }
        return false;
    }
}