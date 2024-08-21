package org.abondar.experimental.travel.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "email")
@Getter
@Setter
public class EmailProperties {

    private Boolean enabled;

    private String to;

    private String subject;
}
