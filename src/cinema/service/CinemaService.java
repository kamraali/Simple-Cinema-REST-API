package cinema.service;

import cinema.exception.WrongTokenException;
import cinema.model.SeatModel;
import cinema.model.StatisticsModel;
import cinema.model.TicketModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private List<SeatModel> seats = new ArrayList<>();
    private List<TicketModel> tickets = new ArrayList<>();

    private final StatisticsModel stats;
    private int totalRows = 9;
    private int totalColumns = 9;
    
    public CinemaService() {

        for (int i = 0; i < totalColumns; i++){
            for (int j = 0; j < totalRows; j++){
                seats.add(new SeatModel(j + 1, i + 1, false, j+1 <=4 ? 10 : 8));
            }
        }

        stats = new StatisticsModel(0,totalColumns * totalRows,0);
    }

    public List<SeatModel> getAllSeats(){
        return seats;
    }
    
    public  List<SeatModel> getAvailableSeats(){
        return seats
                .stream()
                .filter(seat -> seat.isReserved() == false)
                .collect(Collectors.toList());
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public SeatModel getSeatByRowAndColumn(int row, int column){
        return seats.stream()
                .filter(seat->seat.getColumn().equals(column) && seat.getRow().equals(row))
                .findFirst().orElse(null);
    }

    public TicketModel reserveSeat(int row, int column){
        if(getSeatByRowAndColumn(row, column) == null){
            return null;
        }
        if(getSeatByRowAndColumn(row, column).isReserved()){
             return null;
        }
        getSeatByRowAndColumn(row, column).setReserved(true);

        TicketModel ticket = new TicketModel(getSeatByRowAndColumn(row, column));
        tickets.add(ticket);
        stats.seatReserved(ticket.getSeat());

        return ticket;

    }

    public SeatModel returnTicket(String token){
        TicketModel tmp = tickets.stream().filter(ticket -> ticket.getToken().equals(token)).findFirst().orElse(null);

        System.out.println(tickets);
        System.out.println(token);

        if(tmp == null){ return null; }

        tmp.getSeat().setReserved(false);
        stats.seatReturned(tmp.getSeat());

        return tmp.getSeat();
    }

    public StatisticsModel getStats() {
        return stats;
    }
}
