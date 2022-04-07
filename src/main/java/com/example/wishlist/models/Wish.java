package com.example.wishlist.models;

public class Wish {

    private int wishID;
    private int wishlistID;
    private String name;
    private String description;
    private double price;
    private String URL;
    private boolean reservationStatus;
    private String wishNote;

    public Wish(String name, String description, String URL, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.URL = URL;
        this.reservationStatus = false;
    }

    public Wish(int wishID, int wishlistID, String name, String description, double price, String URL, boolean reservationStatus, String wishNote) {
        this.wishID = wishID;
        this.wishlistID = wishlistID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.URL = URL;
        this.reservationStatus = reservationStatus;
        this.wishNote = wishNote;
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

    public String getURL() {
        return URL;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReservationStatus() {
        return reservationStatus;
    }

    public String getWishNote() {
        return wishNote;
    }
}
