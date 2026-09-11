# Support CRM

A full-stack Customer Support CRM system built using Spring Boot, Thymeleaf, MySQL, JPA/Hibernate, and REST APIs.

## Features

- Create support tickets
- Automatically generate unique ticket IDs
- View all support tickets
- Search tickets by:
    - Customer name
    - Ticket ID
    - Customer email
    - Subject
    - Description
- Filter tickets by status
    - Open
    - In Progress
    - Closed
- View complete ticket details
- Update ticket status
- Add notes/comments to tickets
- Delete tickets
- REST API support
- Responsive web interface

## Tech Stack

- Java 26
- Spring Boot 4
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Thymeleaf
- HTML
- CSS
- Maven
- REST APIs
- Git & GitHub

## Project Architecture

Browser  
↓  
Thymeleaf UI  
↓  
Spring MVC / REST Controllers  
↓  
Service Layer  
↓  
JPA Repository  
↓  
MySQL Database

## Database

The application uses MySQL with the following main tables:

- `tickets`
- `notes`

## REST API Endpoints

### Create Ticket

`POST /api/tickets`

### Get Tickets

`GET /api/tickets`

Supports:

- Search
- Status filtering

Example:

`GET /api/tickets?search=payment&status=Open`

### Get Ticket Details

`GET /api/tickets/{ticket_id}`

### Update Ticket

`PUT /api/tickets/{ticket_id}`

Updates ticket status and can add notes.

## Running the Project Locally

### 1. Clone the repository

```bash
git clone https://github.com/dhanu10-04/support-crm.git