package com.mobarok.pragnancytracker.adapter;

public class NoteModel {
    String id,title, symptoms, details, date;

    public NoteModel() {
    }

    public NoteModel(String id, String title, String symptoms, String details, String date) {
        this.id = id;
        this.title = title;
        this.symptoms = symptoms;
        this.details = details;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
