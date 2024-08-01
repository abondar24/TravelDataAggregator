CREATE TABLE hotel_booking (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      city_id BIGINT NOT NULL,
                      hotel_name VARCHAR(255) NOT NULL,
                      number_of_people INT,
                      booking_start TIMESTAMP,
                      booking_end TIMESTAMP,
                      CONSTRAINT fk_city
                          FOREIGN KEY (city_id) REFERENCES city(id)
);