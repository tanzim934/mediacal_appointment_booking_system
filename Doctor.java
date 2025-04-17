public class Doctor {
    private int id;
    private String name;
    private String specialty;

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    @Override
    public String toString() {
        return id + "," + name + "," + specialty;
    }

    public static Doctor fromString(String str) {
        String[] parts = str.split(",");
        return new Doctor(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
}