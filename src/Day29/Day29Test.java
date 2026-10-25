
package Day29;

import Day28.exception.BusinessException;
import Day29.handler.Day29ExceptionHandler;

public class Day29Test {

    public static void main(String[] args) {

        Day29ExceptionHandler handler =
                new Day29ExceptionHandler();

        System.out.println("===== 测试一：正常执行 =====");

        handler.execute(() -> {
            System.out.println("新增学生成功！");
        });

        System.out.println();

        System.out.println("===== 测试二：业务异常 =====");

        handler.execute(() -> {
            throw new BusinessException("学号已经存在！");
        });

        System.out.println();

        System.out.println("===== 测试三：系统异常 =====");

        handler.execute(() -> {
            throw new IllegalStateException("模拟系统错误");
        });
    }
}
