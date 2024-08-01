CREATE TABLE flight_booking (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        destination_city_id BIGINT NOT NULL,
                        flight_number VARCHAR(255) NOT NULL,
                        ticket_number VARCHAR(32) NOT NULL,
                        arrival_time TIMESTAMP,
                        CONSTRAINT fk_destination_city
                            FOREIGN KEY (destination_city_id) REFERENCES city(id)
);