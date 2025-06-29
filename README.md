# Voting Management System

Spring Boot backend for Voting management with:

- Admin management
- Real-time results

## Technologies
- Java 21
- Spring Boot 3.x
- PostgreSQL

## Setup
1. Clone repository
2. Configure `application.properties`
3. Run `VotingappApplication.java`

## API Documentation
`POST /api/auth/login` - User login  
`GET /api/user/options` - Get voting options  
`POST /api/admin/add-option` - Add new candidate (admin only)
