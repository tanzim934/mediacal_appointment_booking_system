import java.util.*;
import java.io.*;

public class MedicalAppointmentSystem {
    private static final String PATIENT_FILE = "patients.txt";
    private static final String APPOINTMENT_FILE = "appointments.txt";
    private static final String DOCTOR_FILE = "doctors.txt";

    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        try {
            doctors = FileManager.readDoctors(DOCTOR_FILE);
            patients = FileManager.readPatients(PATIENT_FILE);
            appointments = FileManager.readAppointments(APPOINTMENT_FILE);

            if (doctors.isEmpty()) {
                doctors.add(new Doctor(1, "Rahul Sharma", "Cardiologist"));
                doctors.add(new Doctor(2, "Anjali Mehta", "Dermatologist"));
                doctors.add(new Doctor(3, "Vikram Patel", "Neurologist"));
                FileManager.writeToFile(doctors, DOCTOR_FILE);
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Medical Appointment Booking System ---");
                System.out.println("1. Register Patient");
                System.out.println("2. Schedule Appointment");
                System.out.println("3. View Appointments");
                System.out.println("4. Edit Appointment");
                System.out.println("5. Cancel Appointment");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: registerPatient(scanner); break;
                    case 2: scheduleAppointment(scanner); break;
                    case 3: viewAppointments(); break;
                    case 4: editAppointment(scanner); break;
                    case 5: cancelAppointment(scanner); break;
                    case 6: FileManager.writeToFile(patients, PATIENT_FILE);
                            FileManager.writeToFile(appointments, APPOINTMENT_FILE);
                            System.out.println("Exiting system. Goodbye!");
                            return;
                    default: System.out.println("Invalid option.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        int id = patients.size() + 1;
        patients.add(new Patient(id, name, age));
        System.out.println("Patient registered with ID: " + id);
    }

    private static void scheduleAppointment(Scanner scanner) {
        System.out.println("Available Doctors:");
        for (Doctor doc : doctors) {
            System.out.println(doc.getId() + ". Dr. " + doc.getName() + " (" + doc.getSpecialty() + ")");
        }

        System.out.print("Enter doctor ID: ");
        int doctorId = scanner.nextInt();

        System.out.println("Registered Patients:");
        for (Patient p : patients) {
            System.out.println(p.getId() + ". " + p.getName());
        }

        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        int id = appointments.size() + 1;
        appointments.add(new Appointment(id, doctorId, patientId, date));
        System.out.println("Appointment scheduled!");
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments.");
            return;
        }

        for (Appointment a : appointments) {
            String doctorName = doctors.stream()
                    .filter(d -> d.getId() == a.getDoctorId())
                    .map(Doctor::getName).findFirst().orElse("Unknown");

            String patientName = patients.stream()
                    .filter(p -> p.getId() == a.getPatientId())
                    .map(Patient::getName).findFirst().orElse("Unknown");

            System.out.println("ID: " + a.getAppointmentId() + ", Dr. " + doctorName +
                    ", Patient: " + patientName + ", Date: " + a.getDate());
        }
    }

    private static void editAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Appointment a : appointments) {
            if (a.getAppointmentId() == id) {
                System.out.print("Enter new date (yyyy-mm-dd): ");
                String newDate = scanner.nextLine();
                a.setDate(newDate);
                System.out.println("Appointment updated.");
                return;
            }
        }

        System.out.println("Appointment not found.");
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID to cancel: ");
        int id = scanner.nextInt();

        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getAppointmentId() == id) {
                iterator.remove();
                System.out.println("Appointment cancelled.");
                return;
            }
        }

        System.out.println("Appointment not found.");
    }
}