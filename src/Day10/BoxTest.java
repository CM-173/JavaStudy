package Day10;

public class BoxTest {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.setValue("张三");
        String name = box.getValue();
        System.out.println(name);
    }
}
