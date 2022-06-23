package cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMetadata {
    private int totalRows;
    private int totalColumns;
    private List<SeatModel> availableSeats;
}
