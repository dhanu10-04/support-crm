package com.dhaneshwari.support_crm.service;

import com.dhaneshwari.support_crm.entity.Note;
import com.dhaneshwari.support_crm.entity.Ticket;
import com.dhaneshwari.support_crm.repository.NoteRepository;
import com.dhaneshwari.support_crm.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final NoteRepository noteRepository;

    // constructor
    public TicketService(TicketRepository ticketRepository, NoteRepository noteRepository) {
        this.ticketRepository = ticketRepository;
        this.noteRepository = noteRepository;
    }
    private void validateStatus(String status) {
        if (!status.equals("Open")
                && !status.equals("In Progress")
                && !status.equals("Closed")) {

            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
    // createTicket()
    public Ticket createTicket(Ticket ticket) {

        ticket.setStatus("Open");

        ticket.setTicketId(
                "TKT-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase()
        );

        return ticketRepository.save(ticket);
    }


    // getAllTickets()
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // searchTickets()
    public List<Ticket> searchTickets(String search){
        if(search==null||search.trim().isEmpty()){
            return ticketRepository.findAll();
        }
        return ticketRepository.searchTickets(search.trim());
    }
    public List<Ticket> getTicketsByStatus(String status) {

        if (status == null || status.trim().isEmpty()) {
            return ticketRepository.findAll();
        }

        return ticketRepository.findByStatusIgnoreCase(status.trim());
    }

    // getTicketById()
    public Ticket getTicketById(String ticketId) {
        return ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found: " + ticketId)
                );
    }

    public List<Ticket> searchByStatus(String search, String status) {

        return ticketRepository.searchByStatus(
                search.trim(),
                status.trim()
        );
    }

    // updateTicket()
    public Ticket updateTicket(String ticketId, String status, String noteText) {
        Ticket ticket = getTicketById(ticketId);
        if (status != null && !status.trim().isEmpty()) {
            status = status.trim();
            validateStatus(status);
            ticket.setStatus(status);
        }
        if (noteText != null && !noteText.trim().isEmpty()) {
            Note note = new Note();
            note.setTicket(ticket);
            note.setNoteText(noteText.trim());
            note.setCreatedAt(LocalDateTime.now());
            noteRepository.save(note);
        }

        return ticketRepository.save(ticket);
    }

    public List<Note> getNotes(String ticketId) {
        getTicketById(ticketId);

        return noteRepository
                .findByTicket_TicketIdOrderByCreatedAtDesc(ticketId);
    }
    public void deleteTicket(String ticketId) {

        Ticket ticket = getTicketById(ticketId);

        List<Note> notes = noteRepository
                .findByTicket_TicketIdOrderByCreatedAtDesc(ticketId);

        if (!notes.isEmpty()) {
            noteRepository.deleteAll(notes);
        }

        ticketRepository.delete(ticket);
    }


}
