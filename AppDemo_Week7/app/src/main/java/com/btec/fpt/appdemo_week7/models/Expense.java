package com.btec.fpt.appdemo_week7.models;


public class Expense {
    private int id;
    private String category;
    private String note;
    private double amount;
    private String date;

    // Constructor
    public Expense(int id, String category, String note, double amount, String date) {
        this.id = id;
        this.category = category;
        this.note = note;
        this.amount = amount;
        this.date = date;
    }

    // Constructor without ID (for inserting new expenses)
    public Expense(String category, String note, double amount, String date) {
        this.category = category;
        this.note = note;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", note='" + note + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}

