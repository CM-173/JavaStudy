
package Day27;

import Day27.controller.Day27StudentController;

public class Day27Test {

    public static void main(String[] args) {

        Day27StudentController controller =
                new Day27StudentController();

        // 测试一：新增一个新学号
        controller.addStudent(
                "T2701",
                "Day27分层测试",
                20
        );

        // 测试二：重复新增相同学号
        controller.addStudent(
                "T2701",
                "重复学号测试",
                21
        );
    }
}
