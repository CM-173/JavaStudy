package Day03;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("张三");
        arrayList.add("李四");
        arrayList.add("王五");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals("李四")){
                iterator.remove();
            }
        }
        for (String name : arrayList){
            System.out.println(name);
        }
    }
}
