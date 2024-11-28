package Model;

public class Device {

    private int id;
    private String type;
    private String brand;
    private String model;
    private boolean activationStatus;
    private int value;
    private int alertValueMax;
    private int alertValueMin;

    public Device(String type, String brand, String model, boolean activationStatus, int value, int alertValueMax, int alertValueMin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.value = value;
        this.alertValueMax = alertValueMax;
        this.alertValueMin = alertValueMin;
    }

    public Device(int id, String type, String brand, String model, boolean activationStatus, int value, int alertValueMax, int alertValueMin) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.value = value;
        this.alertValueMax = alertValueMax;
        this.alertValueMin = alertValueMin;
    }

    public Device(int id, String type, String brand, String model, boolean activationStatus, int alertValueMax, int alertValueMin) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.alertValueMax = alertValueMax;
        this.alertValueMin = alertValueMin;
    }

    public Device(String type, String brand, String model, boolean activationStatus,  int alertValueMax, int alertValueMin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.activationStatus = activationStatus;
        this.alertValueMax = alertValueMax;
        this.alertValueMin = alertValueMin;
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

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getAlertValueMax() {
        return alertValueMax;
    }
    public void setAlertValueMax(int alertValueMax) {
        this.alertValueMax = alertValueMax;
    }

    public int getAlertValueMin() {
        return alertValueMin;
    }
    public void setAlertValueMin(int alertValueMin) {
        this.alertValueMin = alertValueMin;
    }

}
