package com.dhaneshwari.support_crm.repository;

import com.dhaneshwari.support_crm.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findByTicketId(String ticketId);
    @Query("""
        SELECT t FROM Ticket t
        WHERE LOWER(t.customerName) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(t.ticketId) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(t.customerEmail) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(t.subject) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(t.description) LIKE LOWER(CONCAT('%', :search, '%'))
        """)
    List<Ticket> searchTickets(@Param("search") String search);
    List<Ticket> findByStatusIgnoreCase(String status);
    @Query("""
    SELECT t FROM Ticket t
    WHERE t.status = :status
      AND (
           LOWER(t.customerName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.ticketId) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.customerEmail) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.subject) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.description) LIKE LOWER(CONCAT('%', :search, '%'))
      )
    """)
    List<Ticket> searchByStatus(
            @Param("search") String search,
            @Param("status") String status
    );
}
