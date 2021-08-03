package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Api("Schedule Services")
@AllArgsConstructor
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @PostMapping("/addScheduleTennisCourt")
    @ApiOperation("To Add Tennis Court Schedule")
    @ApiResponse(code = 200, message = "Successfully Tennis Court Schedule Created")
    public ResponseEntity<Void> addScheduleTennisCourt(CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @GetMapping("/findSchedulesByDates")
    @ApiOperation("To Find a Schedule In Provided Date Range")
    @ApiResponse(code = 200, message = "Successfully Found Schedule In Provided Date Range")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(name = "startDate") LocalDate startDate,
                                                                  @RequestParam(name = "endDate") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @GetMapping("/findByScheduleId/{scheduleId}")
    @ApiOperation("To Find a Schedule By ID")
    @ApiResponse(code = 200, message = "Successfully Found Schedule By ID")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
