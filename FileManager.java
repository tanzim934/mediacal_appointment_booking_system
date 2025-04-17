import java.io.*;
import java.util.*;

public class FileManager {
    public static List<Doctor> readDoctors(String filename) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return doctors;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            doctors.add(Doctor.fromString(line));
        }
        reader.close();
        return doctors;
    }

    public static List<Patient> readPatients(String filename) throws IOException {
        List<Patient> patients = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return patients;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            patients.add(Patient.fromString(line));
        }
        reader.close();
        return patients;
    }

    public static List<Appointment> readAppointments(String filename) throws IOException {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return appointments;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            appointments.add(Appointment.fromString(line));
        }
        reader.close();
        return appointments;
    }

    public static void writeToFile(List<?> data, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Object obj : data) {
            writer.write(obj.toString());
            writer.newLine();
        }
        writer.close();
    }
}