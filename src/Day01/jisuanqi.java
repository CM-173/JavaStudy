package Day01;

import java.util.Scanner;

public class jisuanqi {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("请输入第一个数字:");
        double a = sc1.nextDouble();
        System.out.println("请输入第二个数字:");
        double b = sc1.nextDouble();
        System.out.println("选择：\n" +
                "1 加法\n" +
                "2 减法\n" +
                "3 乘法\n" +
                "4 除法"
        );
        int c = sc1.nextInt();
        if (c == 1){
            a = a + b;
        } else if (c == 2) {
            a = a - b;
        } else if (c == 3) {
            a = a * b;
        } else if (c == 4) {
            a = a / b;
        }else {
            System.out.println("错误");
        }
        System.out.println("结果：");
        System.out.println(a);

    }
}
