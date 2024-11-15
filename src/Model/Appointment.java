package Model;

public class Appointment {

    private int id;
    private String appointmentDateTime;
    private String doctor;
    private String diagnosis;

    public Appointment(int id, String appointmentDateTime, String doctor, String diagnosis) {
        this.id = id;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public Appointment(String appointmentDateTime, String doctor, String diagnosis) {
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }
    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
