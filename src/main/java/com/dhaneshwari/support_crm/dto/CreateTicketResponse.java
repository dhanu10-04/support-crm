package com.dhaneshwari.support_crm.dto;

import java.time.LocalDateTime;

public class CreateTicketResponse {

    private String ticketId;
    private LocalDateTime createdAt;

    public CreateTicketResponse() {
    }

    public CreateTicketResponse(String ticketId, LocalDateTime createdAt) {
        this.ticketId = ticketId;
        this.createdAt = createdAt;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}