package Day04;

public class Student extends Person{
    private String stuId;

    public Student() {
    }

    public Student(String name, int age, String id) {
        super(name, age);
        this.stuId = id;
    }

    public void study(){
        System.out.println("学习");
    }
    @Override
    public void introduce(){
        System.out.println("我是一个学生");
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
