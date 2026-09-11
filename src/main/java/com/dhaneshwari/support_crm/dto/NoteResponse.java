package com.dhaneshwari.support_crm.dto;

import java.time.LocalDateTime;

public class NoteResponse {
    private String noteText;
    private LocalDateTime createdAt;

    public NoteResponse() {}

    public NoteResponse(String noteText, LocalDateTime createdAt) {
        this.noteText = noteText;
        this.createdAt = createdAt;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
