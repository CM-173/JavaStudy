package Day08.test.io;


import Day08.com.student.entity.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        HashMap<String, Student> hashMap = new HashMap<>();
        FileReader fileReader = new FileReader("data/student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(",");
            Student student = new Student(arr[0], Integer.parseInt(arr[1]), arr[2]);
            System.out.println(student.getId() + student.getName() + student.getAge());
        }
        bufferedReader.close();
    }
}