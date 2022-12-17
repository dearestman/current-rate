package ru.stupakov.currencyrate.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;

/**
 * @author Stupakov D. L.
 **/
@Configuration
@Data
@PropertySource("classpath:application-cb.properties")
public class CBConfig {
    @Value("${cb.url}")
    private String url;
}
