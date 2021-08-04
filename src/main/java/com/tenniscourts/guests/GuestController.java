package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@Api("Guest Management")
@AllArgsConstructor
public class GuestController extends BaseRestController {

    private final GuestService guestService;


    @PostMapping("/addGuest")
    @ApiOperation("To Add New Guest")
    @ApiResponse(code = 200, message = "Successfully Created New Guest")
    public ResponseEntity<GuestDTO> addGuest(GuestDTO guestDTO){
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @GetMapping("/findGuestById/{GuestId}")
    @ApiOperation("To Find Guest By ID")
    @ApiResponse(code = 200, message = "Successfully Found Guest By ID")
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable("GuestId") Long guestId){
        return ResponseEntity.ok(guestService.findGuestById(guestId));
    }

    @GetMapping("/findGuestByName/{GuestName}")
    @ApiOperation("To Find Guest By Name")
    @ApiResponse(code = 200, message = "Successfully Found Guests By Name")
    public ResponseEntity<List<GuestDTO>> findGuestByName(@PathVariable("GuestName") String guestName){
        return ResponseEntity.ok(guestService.findGuestByName(guestName));
    }


    @GetMapping("/findAllGuest")
    @ApiOperation("To All Guest")
    @ApiResponse(code = 200, message = "Successfully Found All Guest")
    public ResponseEntity<List<GuestDTO>> findGuests(){
        return ResponseEntity.ok(guestService.findGuests());
    }

    @PutMapping(value = "/update/{GuestId}")
    @ApiOperation("To Update a Guest")
    @ApiResponse(code = 200, message = "Successfully Updated Guest")
    public ResponseEntity<GuestDTO> updateGuest(GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }


    @DeleteMapping(value = "/delete/{GuestId}")
    @ApiOperation(value="Delete a Guest By Id")
    @ApiResponse(code = 200, message = "Successfully Guest Deleted By ID")
    public ResponseEntity<Void> deleteGuestById(@PathVariable("GuestId") Long guestId) {
        guestService.deleteGuestById(guestId);
        return ResponseEntity.noContent().build();
    }



}
