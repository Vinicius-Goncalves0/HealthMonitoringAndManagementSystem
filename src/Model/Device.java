package Model;

public class Device {

    private String type;
    private String brand;
    private String model;
    private boolean status;
    private int value; //Valores de monitoração do dispositivo

    public Device(String type, String brand, String model, boolean status, int value) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
