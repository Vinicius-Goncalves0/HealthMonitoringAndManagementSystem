package Model;

public class alert {
    private String type;
    private String message;
    private String doctor;
    private String data;

    public alert(String type, String message, String doctor, String data) {
        this.type = type;
        this.message = message;
        this.doctor = doctor;
        this.data = data;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
