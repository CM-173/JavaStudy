package Day28;

import Day28.controller.Day28StudentController;

public class Day28Test {

    public static void main(String[] args) {

        Day28StudentController controller =
                new Day28StudentController();

        // 测试一：正常新增
        controller.addStudent(
                "T2801",
                "Day28异常测试",
                20
        );

        // 测试二：重复学号
        controller.addStudent(
                "T2801",
                "重复学号测试",
                21
        );

        // 测试三：空学号
        controller.addStudent(
                "   ",
                "空学号测试",
                22
        );
    }
}
