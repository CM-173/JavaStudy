package Day07.com.student.dao;

import Day07.com.student.entity.Student;
import java.util.HashMap;

public class HashMapStudentDAO implements StudentDAO {

    private HashMap<String, Student> students = new HashMap<>();

    @Override
    public boolean save(Student student){
        if (students.containsKey(student.getId())){
            return false;
        }else {
            students.put(student.getId(),student);
            return  true;
        }
    }

    @Override
    public HashMap<String, Student> findAll(){
        return students;
    }

    @Override
    public Student findById(String id){
        return students.get(id);
    }

    @Override
    public boolean delete(String id){
        Student student = students.remove(id);
        return student != null;
    }

    @Override
    public boolean update(Student student){
        if(students.containsKey(student.getId())){
            students.put(student.getId(),student);
            return true;
        }
        return false;
    }
}