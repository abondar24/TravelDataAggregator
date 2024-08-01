package org.abondar.experimental.travel.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "batch.local")
@Getter
@Setter
public class BatchLocalProperties {

    private String hotelFile;
}
