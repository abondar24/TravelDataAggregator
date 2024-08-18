CREATE TABLE cruise_booking (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               trip_id VARCHAR(255) NOT NULL,
                               ticket_number VARCHAR(255) NOT NULL ,
                               source_port VARCHAR(32) NOT NULL,
                               destination_port VARCHAR(32) NOT NULL,
                               cruise_start TIMESTAMP,
                               cruise_end TIMESTAMP
);