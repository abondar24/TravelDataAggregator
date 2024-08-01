CREATE TABLE flight_booking (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        destination_city VARCHAR(32),
                        flight_number VARCHAR(255) NOT NULL,
                        ticket_number VARCHAR(32) NOT NULL,
                        arrival_time TIMESTAMP
);