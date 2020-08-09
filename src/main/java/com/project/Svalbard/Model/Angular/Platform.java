package com.project.Svalbard.Model.Angular;

public class Platform {
    private String name;
    private String library;

    public Platform(String name, String library) {
        this.name = name;
        this.library = library;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
