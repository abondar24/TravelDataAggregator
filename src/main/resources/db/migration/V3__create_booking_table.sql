CREATE TABLE booking (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                flight_booking_id BIGINT NOT NULL,
                                hotel_booking_id BIGINT NOT NULL,
                                CONSTRAINT fk_flight_booking
                                    FOREIGN KEY (flight_booking_id) REFERENCES flight_booking(id),
                                CONSTRAINT fk_hotel_booking
                                    FOREIGN KEY (hotel_booking_id) REFERENCES hotel_booking(id)

);