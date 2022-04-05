package com.example.wishlist.models;

public class Wish {

    private int wishID;
    private int wishlistID;
    private String name;
    private String description;
    private String URL;
    private String note;
    private double price;
    private boolean reservationStatus;
    private String wishNote;

    public Wish(String name, String description, String URL, double price) {
        this.name = name;
        this.description = description;
        this.URL = URL;
        this.price = price;
        this.reservationStatus = false;
        this.note = null;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReservationStatus() {
        return reservationStatus;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public String getWishNote() {
        return wishNote;
    }

    public void setWishNote(String wishNote) {
        this.wishNote = wishNote;
    }

    public void setReservationStatus(boolean reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

}
