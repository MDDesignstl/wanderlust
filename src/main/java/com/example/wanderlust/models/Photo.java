package com.example.wanderlust.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "height",
        "htmlAttributions",
        "photoReference",
        "width"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable{

    /** The maximum height of the image. */
    @JsonProperty("height")
    private int height;

    /** Attributions about this listing which must be displayed to the user. */
    @JsonProperty("html_attributions")
    private  String[] htmlAttributions;

    /** Used to identify the photo when you perform a Photo request. */
    @JsonProperty("photo_reference")
    private String photoReference;

    /** The maximum width of the image. */
    @JsonProperty("width")
    private int width;


    public Photo() {
    }

    public Photo(int height, String[] htmlAttributions, String photoReference, int width) {
        this.height = height;
        this.htmlAttributions = htmlAttributions;
        this.photoReference = photoReference;
        this.width = width;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String[] getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(String[] htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }
}
