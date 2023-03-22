package sia.request_response.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class GreetingController {

    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(
            @DestinationVariable String name,
            Mono<String> greetingMono){
        return greetingMono
                .doOnNext(greeting ->
                        log.info("Received a greeting:{} from {}",greeting,name))
                .map(greeting -> "Hello back to you!");
    }



}

