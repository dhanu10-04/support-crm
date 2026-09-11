package com.dhaneshwari.support_crm.repository;

import com.dhaneshwari.support_crm.entity.Note;
import com.dhaneshwari.support_crm.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findByTicket_TicketIdOrderByCreatedAtDesc(String ticketId);
}
