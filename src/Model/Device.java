package Model;

public class Device {

    private int id;
    private String type;
    private String brand;
    private String model;
    private boolean activationStatus;
    private String value;

    public Device(String type, String brand, String model, boolean activationStatus, String value) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.value = value;
    }

    public Device(int id, String type, String brand, String model, boolean activationStatus, String value) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.value = value;
    }

    public Device(int id, String type, String brand, String model, boolean activationStatus) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
    }

    public Device(String type, String brand, String model, boolean activationStatus) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return activationStatus;
    }
    public void setActive(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
