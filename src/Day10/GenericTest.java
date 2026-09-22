package Day10;
import java.util.ArrayList;

public class GenericTest {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();

        names.add("张三");
        names.add("李四");
        names.add("王五");

        String name = names.get(0);

        System.out.println(name);
    }
}