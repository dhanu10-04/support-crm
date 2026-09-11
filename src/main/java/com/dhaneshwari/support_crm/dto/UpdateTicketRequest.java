package com.dhaneshwari.support_crm.dto;

public class UpdateTicketRequest {

    private String status;
    private String notes;

    public UpdateTicketRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}