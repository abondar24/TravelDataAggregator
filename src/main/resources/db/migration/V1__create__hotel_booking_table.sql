CREATE TABLE hotel_booking (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      city VARCHAR(32),
                      hotel_name VARCHAR(255) NOT NULL,
                      number_of_people INT,
                      booking_start TIMESTAMP,
                      booking_end TIMESTAMP
);