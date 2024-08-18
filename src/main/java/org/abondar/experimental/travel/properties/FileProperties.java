package org.abondar.experimental.travel.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileProperties {

    private String hotel;

    private String flight;

    private String cruise;

    //TODO: EXTEND WITH THE OTHER TYPES
}
