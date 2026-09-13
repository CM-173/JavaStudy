package Day03;

public class Student {
    private String name;
    private int age;
    private String id;

    public Student(String name,int age,String id){
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        if (age < 0 || age > 150){
            System.out.println("年龄不合法");
            return;
        }else {
            this.age = age;
        }
    }
    public void setId(String id){
        this.id = id;
    }
}
