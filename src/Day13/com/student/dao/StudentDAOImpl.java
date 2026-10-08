package Day13.com.student.dao;

import Day13.com.student.entity.Student;
import Day13.com.student.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    //查询所有学生
    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, age FROM student";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                Student student = new Student(id, name, age);
                students.add(student);
            }
        }

        return students;
    }

    //添加学生
    @Override
    public boolean save(Student student) throws SQLException {
        String sql = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());

            return statement.executeUpdate() > 0;
        }
    }

    //根据学号查询
    @Override
    public Student findById(String id) throws SQLException {
        String sql = "SELECT id, name, age FROM student WHERE id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");

                    return new Student(id, name, age);
                }
            }
        }

        return null;
    }

    //修改学生
    @Override
    public boolean update(Student student) throws SQLException {
        String sql = "UPDATE student SET name = ?, age = ? WHERE id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getId());

            return statement.executeUpdate() > 0;
        }
    }

    //删除学生
    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            return statement.executeUpdate() > 0;
        }
    }
}