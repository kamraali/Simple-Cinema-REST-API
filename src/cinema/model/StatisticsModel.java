package cinema.model;

import lombok.*;


@Data
@Getter
@AllArgsConstructor
public class StatisticsModel {
    private Integer currentIncome;
    private Integer numberOfAvailableSeats;
    private Integer numberOfPurchasedTickets;


    public void seatReserved(SeatModel seat){
        numberOfPurchasedTickets++;
        numberOfAvailableSeats--;
        currentIncome += seat.getPrice();
    }

    public void seatReturned(SeatModel seat){
        numberOfPurchasedTickets--;
        numberOfAvailableSeats++;
        currentIncome -= seat.getPrice();
    }
}
