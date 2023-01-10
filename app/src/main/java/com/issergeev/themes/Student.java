package com.issergeev.themes;

public class Student {
    public String getName() {
        return name;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String name;
    private String lastName;

    public String getThemeID() {
        return themeID;
    }

    public void setThemeID(String themeID) {
        this.themeID = themeID;
    }

    private String themeID;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    Student(String name, String lastName, String themeID, String login) {
        this.name = name;
        this.lastName = lastName;
        this.themeID = themeID;
        this.login = login;
    }
}