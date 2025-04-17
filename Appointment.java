
public class Appointment {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private String date;

    public Appointment(int appointmentId, int doctorId, int patientId, String date) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
    }

    public int getAppointmentId() { return appointmentId; }
    public int getDoctorId() { return doctorId; }
    public int getPatientId() { return patientId; }
    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return appointmentId + "," + doctorId + "," + patientId + "," + date;
    }

    public static Appointment fromString(String str) {
        String[] parts = str.split(",");
        return new Appointment(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                parts[3]);
    }
}