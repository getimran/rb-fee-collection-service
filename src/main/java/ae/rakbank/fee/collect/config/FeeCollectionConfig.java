package ae.rakbank.fee.collect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeeCollectionConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
