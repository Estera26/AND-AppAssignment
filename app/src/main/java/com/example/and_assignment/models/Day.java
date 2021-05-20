package com.example.and_assignment.models;

public class Day {
    private String name;
    private String picture;
    private String description;
    private String video;
    private String description2;

    public Day() {
    }

    public Day(String name, String picture, String description, String video,String description2) {
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.video = video;
        this.description2 = description2;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription2() { return description2; }
}
