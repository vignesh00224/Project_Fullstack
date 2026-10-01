package Proj1;

public class Studentobj {

    private int id;
    private String name;
    private int age;
    private String city;

    public Studentobj(int id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    // Update student details

    public void updstd(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{ id : " + id
                + ", name : " + name
                + ", age : " + age
                + ", city : " + city + " }";
    }
}