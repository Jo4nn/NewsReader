package com.example.joannapacia.test.model;

/**
 * Created by joannapacia on 07/03/17.
 */

public class LinkData {
    private String headline;
    private String desciption;
    private String image_url;
    private String link;

    // non-argument constructor
    public LinkData(){};

    public LinkData(String headline, String desciption, String image_url) {
        this.headline = headline;
        this.desciption = desciption;
        this.image_url = image_url;
    }

    public LinkData(String headline, String desciption, String image_url, String link) {
        this.headline = headline;
        this.desciption = desciption;
        this.image_url = image_url;
        this.link = link;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String name) {
        this.headline = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String email) {
        this.desciption = email;
    }

    public String getImage() {
        return image_url;
    }

    public void setImage(String image_url) {
        this.image_url = image_url;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }
}