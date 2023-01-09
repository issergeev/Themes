package com.issergeev.themes;

public class Theme {
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    private String themeName;

    Theme(String themeName) {
        this.themeName = themeName;
    }
}