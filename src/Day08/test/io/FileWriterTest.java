package Day08.test.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("data/student.txt",true);

        fileWriter.write("张三,20,001");
        fileWriter.write("\n");
        fileWriter.write("李四,19,002");
        fileWriter.write("\n");
        fileWriter.write("王五,21,003");
        fileWriter.write("\n");

        fileWriter.close();
    }
}
