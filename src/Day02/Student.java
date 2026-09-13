package Day02;

/** 学生信息实体类。 */
public class Student {
    private String name;
    private int age;
    private final String id;

    public Student(String name, int age, String id) {
        setName(name);
        setAge(age);
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("学号不能为空");
        }
        this.id = id.trim();
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getId() { return id; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age < 1 || age > 150) {
            throw new IllegalArgumentException("年龄应在 1 到 150 之间");
        }
        this.age = age;
    }
}
