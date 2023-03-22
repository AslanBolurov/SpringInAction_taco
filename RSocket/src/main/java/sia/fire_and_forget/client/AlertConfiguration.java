package sia.fire_and_forget.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import sia.fire_and_forget.model.Alert;

import java.time.Instant;

@Slf4j
@Configuration
public class AlertConfiguration {

    @Bean
    public ApplicationRunner sender(
            RSocketRequester.Builder requestBuilder
    ) {
        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);

            tcp
                    .route("alert")
                    .data(new Alert(Alert.Level.RED, "Craig", Instant.now()))
                    .send()
                    .subscribe();
            log.info("Alert sent");
        };


    }


}
