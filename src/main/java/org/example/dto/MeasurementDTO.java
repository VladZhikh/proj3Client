package org.example.dto;

import java.time.LocalDateTime;

public class MeasurementDTO {
    //{"id":5,"value":13.5,"raining":true,"createdAt":"2023-10-20T16:12:08.388954","sensor":{"id":3,"name":"sensor_1"}}
    private int id;
    private double value;
    private boolean raining;
    private LocalDateTime createdAt;
    private SensorDTO sensor;

    public MeasurementDTO(int id,double value, boolean raining, LocalDateTime createdAt, SensorDTO sensor) {
        this.id=id;
        this.value = value;
        this.raining = raining;
        this.createdAt = createdAt;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                ", sensor=" + sensor +
                '}';
    }
}
