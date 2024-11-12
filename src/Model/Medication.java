package Model;

import java.time.LocalDate;

public class Medication {
    private String name;
    private int dosage;
    private int frequency;
    private String description;
    private String doctor;
    private LocalDate prescriptionDate;

    public Medication(String name, int dosage, int frequency, String description, String doctor, LocalDate prescriptionDate) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.description = description;
        this.doctor = doctor;
        this.prescriptionDate = prescriptionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }
}
