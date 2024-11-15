package Model;

public class Patient {

    private int id;
    private String name;
    private String CPF;
    private String birthDate;
    private String address;
    private String phone;
    private String email;
    private String histories;


    public Patient(String name, String CPF, String birthDate, String address, String phone, String email, String histories) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.histories = histories;
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

    public String getHistories() {
        return histories;
    }
    public void addHistories(String histories) {
        this.histories = histories;
    }
}