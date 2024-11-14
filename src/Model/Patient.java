package Model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private int id;
    private String name;
    private String CPF;
    private String birthDate;
    private String address;
    private String phone;
    private String email;
    // banco de dados em lista -> de History (registros de doenças anteriores)
    private List<History> histories;
    // banco de dados em lista -> de Medicines (referência a prescrições)
    private List<Medication> medications;
    // banco de dados em lista -> de Devices (referência aos dispositivos usados para monitoramento)
    private List<Device> devices;


    public Patient(String name, String CPF, String birthDate, String address, String phone, String email) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.histories = new ArrayList<>(); // Inicializa a lista de históricos
        this.medications = new ArrayList<>(); // Inicializa a lista de medicamentos
        this.devices = new ArrayList<>(); // Inicializa a lista de dispositivos
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // get e add para History
    public List<History> getHistories() {
        return histories;
    }
    public void addHistory(History histories) {
        this.histories.add(histories);
    }
    public void removeHistory(History histories) {
        this.histories.remove(histories);
    }

    // get e add para Medication
    public List<Medication> getMedication() {
        return medications;
    }
    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }
    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
    }

    // get e add para Devices
    public List<Device> getDevice() {
        return devices;
    }
    public void addDevice(Device devices) {
        this.devices.add(devices);
    }
    public void removeDevice(Device devices) {
        this.devices.remove(devices);
    }
}