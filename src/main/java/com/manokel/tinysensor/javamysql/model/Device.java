package com.manokel.tinysensor.javamysql.model;

public class Device {
    private int deviceId;
    private String model;
    private int inputs;
    private String mac;

    public Device() {}

    public Device(int deviceId, String model, int inputs, String mac) {
        this.deviceId = deviceId;
        this.model = model;
        this.inputs = inputs;
        this.mac = mac;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
