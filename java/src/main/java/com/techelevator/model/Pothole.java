package com.techelevator.model;

import java.math.BigDecimal;

public class Pothole {

    private int potHoleId;
    private int userId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String currentStatus;
    private String reportedDate;
    private String inspectedDate;
    private String repairedDate;

    // adding severity property
    private String severity;

    public Pothole(int userId, BigDecimal latitude, BigDecimal longitude, String currentStatus, String reportedDate, String inspectedDate, String repairedDate) {
        //this.potHoleId = potHoleId;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentStatus = currentStatus;
        this.reportedDate = reportedDate;
        this.inspectedDate = inspectedDate;
        this.repairedDate = repairedDate;
        // adding severity to constructor
        this.severity = severity;
    }
    public Pothole(){

    }

    public int getPotHoleId() {
        return potHoleId;
    }

    public void setPotHoleId(int potHoleId) {
        this.potHoleId = potHoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(String inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getRepairedDate() {
        return repairedDate;
    }

    public void setRepairedDate(String repairedDate) {
        this.repairedDate = repairedDate;
    }

    // adding getter and setter for severity add-on
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
