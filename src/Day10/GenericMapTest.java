package Day10;

import java.util.HashMap;

public class GenericMapTest {

    public static void main(String[] args) {


        HashMap<String,Integer> map = new HashMap<>();


        map.put("张三",20);
        map.put("李四",21);



        int age = map.get("张三");


        System.out.println(age);

    }
}