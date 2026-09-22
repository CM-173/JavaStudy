package Day10;

public class GenericMethodTest {
    public static void main(String[] args) {
        print("java");
        print(123);
        print(3.14);
    }
    public static <T> void print(T value){
        System.out.println(value);
    }
}
