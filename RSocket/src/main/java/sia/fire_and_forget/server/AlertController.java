package sia.fire_and_forget.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import sia.fire_and_forget.model.Alert;

@Slf4j
@Controller
public class AlertController {

    @MessageMapping("alert")
    public Mono<Void> setAlert(Mono<Alert> alertMono){
        return alertMono
                .doOnNext(alert ->
                        log.info("{} alert by {} at {}",
                                alert.getLevel(),
                                alert.getOrderBy(),
                                alert.getOrderedAt())
                )
                .thenEmpty(Mono.empty());
    }
}
