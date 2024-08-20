CREATE TABLE trip_info (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  trip_id VARCHAR(255) NOT NULL,
                                  saving_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  UNIQUE (trip_id)
);