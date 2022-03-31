package com.example.wishlist.models;

public class Wish {

    private String name;
    private String description;
    private String URL;
    private double price;
    private boolean reservationStatus;

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

    public void setReservationStatus(boolean reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Wish(String name, String description, String URL, double price) {
        this.name = name;
        this.description = description;
        this.URL = URL;
        this.price = price;
        this.reservationStatus = false;
    }
}
