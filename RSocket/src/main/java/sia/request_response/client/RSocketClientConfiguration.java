package sia.request_response.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class RSocketClientConfiguration {

    @Bean
    public ApplicationRunner sender(
            RSocketRequester.Builder requestBuilder
    ) {
        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);

            String who = "Craig";
            tcp
                    .route("greeting/{name}", who)
                    .data("Hello RSocket!")
                    .retrieveMono(String.class)
                    .subscribe(response -> log.info("Got a response:{}", response));
        };


    }


}
