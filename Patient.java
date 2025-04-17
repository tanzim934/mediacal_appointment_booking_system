public class Patient {
    private int id;
    private String name;
    private int age;

    public Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return id + "," + name + "," + age;
    }

    public static Patient fromString(String str) {
        String[] parts = str.split(",");
        return new Patient(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }
}