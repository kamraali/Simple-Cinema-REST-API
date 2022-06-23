package cinema.controller;

import cinema.exception.*;
import cinema.model.*;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@Validated
public class CinemaController {

    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public ResponseEntity<ResponseMetadata> getSeats(){

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMetadata(service.getTotalRows(), service.getTotalColumns(), service.getAvailableSeats()));
    }

    @PostMapping("/purchase")
    public ResponseEntity<TicketModel> reserveSeat(@RequestBody Map<String, Object> seatMap){

        int row = Integer.parseInt(seatMap.get("row").toString());
        int column = Integer.parseInt(seatMap.get("column").toString());

        if(row < 0 || row >= service.getTotalRows() || column < 0 || column >= service.getTotalColumns()){
            throw new OutOfBoundsException();
        }

        TicketModel ticket = service.reserveSeat(row, column);

        if(ticket == null){
            System.out.println("Throws");
            throw new AlreadySoldException();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ticket);
        }


    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, SeatModel>> returnTicket(@RequestBody Map<String, Object> tokenMap) {

        String token = tokenMap.get("token").toString();
        SeatModel tmp = service.returnTicket(token);

        if(tmp == null) { throw new WrongTokenException(); }

        Map<String, SeatModel> returnedTicket = new HashMap<>();
        returnedTicket.put("returned_ticket", tmp);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(returnedTicket);
    }

    @PostMapping("/stats")
    public ResponseEntity getStatistics(@RequestParam(value = "password", required = false) String password){
        System.out.println("Code is here");

        if(password != null) {
            if (password.equals("super_secret")) {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getStats());
            }
            System.out.println("Code is here 1");
            throw new WrongPasswordException();
        }
        System.out.println("Code is here 2");
        throw new WrongPasswordException();
    }

    @ExceptionHandler(BusinessLogicException.class)
    ResponseEntity<Object> errorHandler(BusinessLogicException e) {

        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return  new ResponseEntity<Object>(
                map, new HttpHeaders(), e.getErrorStatus());
    }
}
