package com.example.letsgo.Controller;

import com.example.letsgo.Dto.RideDetailsRequestDto;
import com.example.letsgo.Dto.RiderGraph.RiderGraphResponseDto;
import com.example.letsgo.Service.RiderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
public class RideDetailsController {

    private final RiderDetailService riderDetailService;

    public RideDetailsController(RiderDetailService riderDetailService) {
        this.riderDetailService = riderDetailService;
    }

    @PostMapping
//    @ApiOperation(value = "Getting a Unique Ride number" , notes = "Customer is  sending all the details regarding location and price and getting unique ride number as response")
    public ResponseEntity<String> bookRide(@RequestBody RideDetailsRequestDto rideDetailsRequestDto){
        String rideNumber = riderDetailService.generateRideNumber(rideDetailsRequestDto);
        return ResponseEntity.ok(rideNumber);
    }

//    @ApiOperation(latitude = "Getting users departure latitude" , longitude = "Getting users departure latitude", response will contain all riders with no of riders.)
    @GetMapping("fetchRiders/{latitude}/{longitude}")
    public ResponseEntity<RiderGraphResponseDto> fechRiders(@PathVariable double userLatitude, @PathVariable double userLongitude) {
        RiderGraphResponseDto response = riderDetailService.getAllRiders(userLatitude, userLongitude);
        return ResponseEntity.ok(response);
    }
}
