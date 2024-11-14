package Model;

public class Medication {
    private int id;
    private String patientName;
    private String medicationName;
    private int dosage;
    private int frequency;
    private String description;
    private String doctor;
    private String prescriptionDate;

    public Medication(String medicationName, int dosage, int frequency, String description, String doctor, String prescriptionDate) {
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.description = description;
        this.doctor = doctor;
        this.prescriptionDate = prescriptionDate;
    }
    
    public Medication(String patientName, String medicationName, int dosage, int frequency, String description, String doctor, String prescriptionDate) {
        this.patientName = patientName;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.description = description;
        this.doctor = doctor;
        this.prescriptionDate = prescriptionDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicationName() {
        return medicationName;
    }
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getDosage() {
        return dosage;
    }
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }
    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }
}
