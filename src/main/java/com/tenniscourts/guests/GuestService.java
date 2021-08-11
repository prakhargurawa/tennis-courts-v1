package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

    private final GuestRepository guestRepository;

    private final GuestMapper guestMapper;

    public GuestDTO addGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(guestDTO)));
    }

    public GuestDTO findGuestById(Long guestId){
        return guestRepository.findById(guestId).map(guestMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        });
    }

    public List<GuestDTO> findGuestByName(String name){
        return guestRepository.findByName(name).stream().map(guestMapper::map).collect(Collectors.toList());
    }

    public List<GuestDTO> findGuests(){
        return guestRepository.findAll().stream().map(guestMapper::map).collect(Collectors.toList());
    }

    public GuestDTO updateGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.findById(guestDTO.getId())
                .map(guest -> {
                    guest.setName(guestDTO.getName());
                    return guestRepository.save(guest);
                }).orElseThrow(() -> new EntityNotFoundException("Guest not found.")));
    }

    public void deleteGuestById(Long guestId) {
        Guest deletingGuest = guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest was not found."));

        guestRepository.delete(deletingGuest);
    }



}

