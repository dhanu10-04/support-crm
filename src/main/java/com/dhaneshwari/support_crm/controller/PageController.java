package com.dhaneshwari.support_crm.controller;

import com.dhaneshwari.support_crm.dto.CreateTicketRequest;
import com.dhaneshwari.support_crm.dto.NoteResponse;
import com.dhaneshwari.support_crm.dto.TicketDetailResponse;
import com.dhaneshwari.support_crm.dto.TicketListResponse;
import com.dhaneshwari.support_crm.dto.UpdateTicketRequest;
import com.dhaneshwari.support_crm.entity.Note;
import com.dhaneshwari.support_crm.entity.Ticket;
import com.dhaneshwari.support_crm.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PageController {

    private final TicketService ticketService;

    public PageController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Dashboard
    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            Model model) {

        List<Ticket> tickets;

        if (search != null && !search.trim().isEmpty()
                && status != null && !status.trim().isEmpty()) {

            tickets = ticketService.searchByStatus(search, status);

        } else if (status != null && !status.trim().isEmpty()) {

            tickets = ticketService.getTicketsByStatus(status);

        } else {

            tickets = ticketService.searchTickets(search);
        }

        List<TicketListResponse> ticketList = tickets.stream()
                .map(ticket -> new TicketListResponse(
                        ticket.getTicketId(),
                        ticket.getCustomerName(),
                        ticket.getSubject(),
                        ticket.getStatus(),
                        ticket.getCreatedAt()
                ))
                .toList();

        model.addAttribute("tickets", ticketList);
        model.addAttribute("search", search);
        model.addAttribute("status", status);

        return "index";
    }

    // Show create ticket page
    @GetMapping("/tickets/new")
    public String showCreateTicketForm(Model model) {

        model.addAttribute("createTicketRequest", new CreateTicketRequest());

        return "create-ticket";
    }

    // Create ticket
    @PostMapping("/tickets")
    public String createTicket(
            @Valid @ModelAttribute("createTicketRequest")
            CreateTicketRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create-ticket";
        }

        Ticket ticket = new Ticket();

        ticket.setCustomerName(request.getCustomerName());
        ticket.setCustomerEmail(request.getCustomerEmail());
        ticket.setSubject(request.getSubject());
        ticket.setDescription(request.getDescription());

        Ticket createdTicket = ticketService.createTicket(ticket);

        return "redirect:/tickets/" + createdTicket.getTicketId();
    }

    // Show ticket details
    @GetMapping("/tickets/{ticketId}")
    public String showTicketDetails(
            @PathVariable String ticketId,
            Model model) {

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

        TicketDetailResponse ticketDetails = new TicketDetailResponse(
                ticket.getTicketId(),
                ticket.getCustomerName(),
                ticket.getCustomerEmail(),
                ticket.getSubject(),
                ticket.getDescription(),
                ticket.getStatus(),
                noteResponses
        );

        model.addAttribute("ticket", ticketDetails);

        return "ticket-detail";
    }

    // Update ticket status / add note
    @PostMapping("/tickets/{ticketId}/update")
    public String updateTicket(
            @PathVariable String ticketId,
            @ModelAttribute UpdateTicketRequest request) {

        ticketService.updateTicket(
                ticketId,
                request.getStatus(),
                request.getNotes()
        );

        return "redirect:/tickets/" + ticketId;
    }
    @PostMapping("/tickets/{ticketId}/delete")
    public String deleteTicket(@PathVariable String ticketId) {

        ticketService.deleteTicket(ticketId);

        return "redirect:/";
    }
}