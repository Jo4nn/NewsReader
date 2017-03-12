package com.example.joannapacia.test.model;

/**
 * Created by joannapacia on 08/03/17.
 */
public class SectionData {

    // Getter and Setter model for recycler view items
    private String title;
    private int image;

    public SectionData(String title,  int image) {

        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
