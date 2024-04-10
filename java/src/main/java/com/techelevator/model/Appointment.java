package com.techelevator.model;

public class Appointment {

    private int appointmentId;
    private int potHoleId;
    private int userId;
    private String appointmentType;
    private String appointmentDate;
    private String completionStatus;

    public Appointment(int appointmentId, int potHoleId, int userId, String appointmentType, String appointmentDate, String completionStatus) {
        this.appointmentId = appointmentId;
        this.potHoleId = potHoleId;
        this.userId = userId;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.completionStatus = completionStatus;
    }

    public Appointment() {

    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPotHoleId() {
        return potHoleId;
    }

    public void setPotHoleId(int potHoleId) {
        this.potHoleId = potHoleId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
