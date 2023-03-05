package com.manokel.tinysensor.javamysql.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Dataset {
    // Creating a list of valid tags which are recognised
    // final String[] tagArray = { "datasetId", "temperature", "humidity","pressure",
    //                      "light", "red", "green", "blue", "milliseconds"};
    // ArrayList<String> sensorData = new ArrayList<String>(Arrays.asList(tagArray));
    private int datasetId;
    private double[][] dataArray;

    public Dataset() {
    }

    public Dataset(int datasetId, double[][] dataArray) {
        this.datasetId = datasetId;
        this.dataArray = dataArray;
    }

    public int getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(int datasetId) {
        this.datasetId = datasetId;
    }

    public double[][] getDataArray() {
        return dataArray;
    }

    public void setDataArray(double[][] dataArray) {
        this.dataArray = dataArray;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "datasetId=" + datasetId +
                ", dataArray=" + Arrays.toString(dataArray) +
                '}';
    }
}
