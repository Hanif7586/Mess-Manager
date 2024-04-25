package com.HRTSoft.mealmanaging;

public class Modelm {
    private int id;
    private String firstname;
    private String motMeal;
    private String motJoma;
    private String mealRatesave;
    private String motKhoroj;
    private String Managerpabe;
    private String Managerdibe;

    public Modelm(int id, String firstname, String motMeal, String motJoma, String mealRatesave, String motKhoroj, String managerpabe, String managerdibe) {
        this.id = id;
        this.firstname = firstname;
        this.motMeal = motMeal;
        this.motJoma = motJoma;
        this.mealRatesave = mealRatesave;
        this.motKhoroj = motKhoroj;
        Managerpabe = managerpabe;
        Managerdibe = managerdibe;
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

    public String getMotMeal() {
        return motMeal;
    }

    public void setMotMeal(String motMeal) {
        this.motMeal = motMeal;
    }

    public String getMotJoma() {
        return motJoma;
    }

    public void setMotJoma(String motJoma) {
        this.motJoma = motJoma;
    }

    public String getMealRatesave() {
        return mealRatesave;
    }

    public void setMealRatesave(String mealRatesave) {
        this.mealRatesave = mealRatesave;
    }

    public String getMotKhoroj() {
        return motKhoroj;
    }

    public void setMotKhoroj(String motKhoroj) {
        this.motKhoroj = motKhoroj;
    }

    public String getManagerpabe() {
        return Managerpabe;
    }

    public void setManagerpabe(String managerpabe) {
        Managerpabe = managerpabe;
    }

    public String getManagerdibe() {
        return Managerdibe;
    }

    public void setManagerdibe(String managerdibe) {
        Managerdibe = managerdibe;
    }
}
