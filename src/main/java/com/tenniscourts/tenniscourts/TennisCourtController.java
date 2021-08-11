package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("Tennis Court Controller")
@AllArgsConstructor
@RestController // https://www.baeldung.com/spring-controller-vs-restcontroller
@RequestMapping("/tennis-court")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @PostMapping("/addTennisCourt")
    @ApiOperation("To Add Tennis Court")
    @ApiResponse(code = 200, message = "Successfully Tennis Court Created")
    public ResponseEntity<Void> addTennisCourt(TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @GetMapping("/findTennisCourtById/{tennisCourtId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("To Find Tennis Court By ID")
    @ApiResponse(code = 200, message = "Successfully Found Tennis Court By ID")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable("tennisCourtId") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }


    @GetMapping("/findTennisCourtWithSchedulesById/{tennisCourtId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find Tennis Court Schedule By ID")
    @ApiResponse(code = 200, message = "Successfully Found Tennis Court Schedule By ID")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable("tennisCourtId") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
