package com.issergeev.themes;

public class Theme {
    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    private String themeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    Theme(long id, String themeName) {
        this.themeName = themeName;
        this.id = id;
    }
}