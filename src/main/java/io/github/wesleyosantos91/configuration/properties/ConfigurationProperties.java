package io.github.wesleyosantos91.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
public class ConfigurationProperties {

    @Value("${app.metrics.timeout}")
    private Integer timeout;
}
