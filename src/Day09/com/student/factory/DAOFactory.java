package Day09.com.student.factory;

import Day09.com.student.dao.StudentDAO;
import Day09.com.student.dao.StudentDAOImpl;

public class DAOFactory {

    private static StudentDAO studentDAO
            = new StudentDAOImpl();

    public static StudentDAO getStudentDAO(){
        return studentDAO;
    }

}