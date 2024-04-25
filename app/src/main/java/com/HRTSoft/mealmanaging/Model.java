package com.HRTSoft.mealmanaging;

public class Model {
    private int id;
    private String firstname;
    private String lastname;
    private String spinnerValue; // Add this field for the Spinner

    public Model(int id, String firstname, String lastname, String spinnerValue) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.spinnerValue = spinnerValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpinnerValue() {
        return spinnerValue;
    }

    public void setSpinnerValue(String spinnerValue) {
        this.spinnerValue = spinnerValue;
    }
}
