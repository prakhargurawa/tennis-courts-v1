package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("Reservation Controller")
@AllArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping("/bookReservation")
    @ApiOperation("To Book a Reservation")
    @ApiResponse(code = 200, message = "Successfully Booked a Reservation")
    public ResponseEntity<Void> bookReservation(@Valid CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping("/findReservation/{reservationId}")
    @ApiOperation("To Find Reservation")
    @ApiResponse(code = 200, message = "Successfully Found Reservation By ID")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @DeleteMapping(value = "/cancelReservation/{reservationId}")
    @ApiOperation(value="To Cancel Reservation")
    @ApiResponse(code = 200, message = "Successfully Cancelled Reseravtion")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping(value = "/rescheduleReservation")
    @ApiOperation(value="To Reschedule a Reservation")
    @ApiResponse(code = 200, message = "Reservation cancelled")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@RequestParam(name = "reservationId") Long reservationId,@RequestParam(name = "scheduleId")  Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
