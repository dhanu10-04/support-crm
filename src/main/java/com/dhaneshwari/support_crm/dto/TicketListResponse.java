package com.dhaneshwari.support_crm.dto;

import java.time.LocalDateTime;

public class TicketListResponse {

    private String ticketId;
    private String customerName;
    private String subject;
    private String status;
    private LocalDateTime createdAt;

    public TicketListResponse() {
    }

    public TicketListResponse(String ticketId, String customerName,
                              String subject, String status,
                              LocalDateTime createdAt) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.subject = subject;
        this.status = status;
        this.createdAt = createdAt;
    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}