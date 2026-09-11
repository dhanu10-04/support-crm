package com.dhaneshwari.support_crm.dto;

import java.util.List;

public class TicketDetailResponse {
    private String ticketId;
    private String customerName;
    private String customerEmail;
    private String subject;
    private String description;
    private String status;
    private List<NoteResponse> notes;

    public TicketDetailResponse(String ticketId, String customerName, String customerEmail, String subject, String description, String status, List<NoteResponse> notes) {

        this.ticketId = ticketId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.notes = notes;
    }

    public TicketDetailResponse() {}

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NoteResponse> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteResponse> notes) {
        this.notes = notes;
    }
}

