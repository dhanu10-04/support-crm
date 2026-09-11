package com.dhaneshwari.support_crm.restcontroller;

import com.dhaneshwari.support_crm.dto.*;
import com.dhaneshwari.support_crm.entity.Note;
import com.dhaneshwari.support_crm.entity.Ticket;
import com.dhaneshwari.support_crm.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    
    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PostMapping()
    public ResponseEntity<CreateTicketResponse> createTicket(
            @Valid @RequestBody CreateTicketRequest createTicketRequest) {
        //request  DTO-->Entity
        Ticket ticket=new Ticket();
        ticket.setCustomerName(createTicketRequest.getCustomerName());
        ticket.setCustomerEmail(createTicketRequest.getCustomerEmail());
        ticket.setSubject(createTicketRequest.getSubject());
        ticket.setDescription(createTicketRequest.getDescription());

        //Controller--->service
        Ticket ticketCreated=ticketService.createTicket(ticket);

        //Entity->rsponse dto
        CreateTicketResponse createTicketResponse=new CreateTicketResponse(
                ticketCreated.getTicketId(),
                ticketCreated.getCreatedAt()
        );
        return ResponseEntity.ok(createTicketResponse);

    }


    @GetMapping
    public ResponseEntity<List<TicketListResponse>> getTickets(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {

        List<Ticket> tickets;

        if (search != null && !search.trim().isEmpty()
                && status != null && !status.trim().isEmpty()) {

            tickets = ticketService.searchByStatus(search, status);

        } else if (status != null && !status.trim().isEmpty()) {

            tickets = ticketService.getTicketsByStatus(status);

        } else {

            tickets = ticketService.searchTickets(search);
        }

        List<TicketListResponse> responses = tickets.stream()
                .map(ticket -> new TicketListResponse(
                        ticket.getTicketId(),
                        ticket.getCustomerName(),
                        ticket.getSubject(),
                        ticket.getStatus(),
                        ticket.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{ticket_id}")
    public ResponseEntity<TicketDetailResponse> getTicketById(
            @PathVariable("ticket_id") String ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        List<Note> notes = ticketService.getNotes(ticketId);

        List<NoteResponse> noteResponses = notes.stream()
                .map(note -> {
                    NoteResponse response = new NoteResponse();
                    response.setNoteText(note.getNoteText());
                    response.setCreatedAt(note.getCreatedAt());
                    return response;
                })
                .toList();

        TicketDetailResponse response = new TicketDetailResponse(
                ticket.getTicketId(),
                ticket.getCustomerName(),
                ticket.getCustomerEmail(),
                ticket.getSubject(),
                ticket.getDescription(),
                ticket.getStatus(),
                noteResponses
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{ticket_id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable("ticket_id") String ticketId,
            @RequestBody UpdateTicketRequest request) {

        Ticket updatedTicket = ticketService.updateTicket(
                ticketId,
                request.getStatus(),
                request.getNotes()
        );

        return ResponseEntity.ok(updatedTicket);
    }


}
