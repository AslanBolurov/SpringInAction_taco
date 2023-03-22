package sia.fire_and_forget.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Alert {

    private Level level;
    private String orderBy;
    private Instant orderedAt;

   public enum Level{
       YELLOW, ORANDE, RED, BLACK
   }
}
