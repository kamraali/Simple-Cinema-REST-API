package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class TicketModel {

    private String token;
    @JsonProperty("ticket")
    private SeatModel seat;

    public TicketModel(SeatModel seat) {
        this.token = UUID.randomUUID().toString();
        this.seat = seat;
    }
}
