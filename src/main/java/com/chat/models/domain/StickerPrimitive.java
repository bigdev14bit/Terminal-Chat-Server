package com.chat.models.domain;

public class StickerPrimitive {
    private String stickerId;
    private String name;
    private String asciiArt;
    private String category;

    public StickerPrimitive() {}

    public StickerPrimitive(String stickerId, String name, String asciiArt, String category) {
        this.stickerId = stickerId;
        this.name = name;
        this.asciiArt = asciiArt;
        this.category = category;
    }

    public String getStickerId() {
        return stickerId;
    }

    public void setStickerId(String stickerId) {
        this.stickerId = stickerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiArt() {
        return asciiArt;
    }

    public void setAsciiArt(String asciiArt) {
        this.asciiArt = asciiArt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
