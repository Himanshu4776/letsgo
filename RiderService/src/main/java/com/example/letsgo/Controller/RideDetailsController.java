package com.example.letsgo.Controller;

import com.example.letsgo.Dto.RideDetailsRequestDto;
import com.example.letsgo.Dto.RiderGraph.RiderGraphResponseDto;
import com.example.letsgo.Service.Impl.UserDetailServiceImpl;
import com.example.letsgo.Service.UserDetailService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.hibernate.annotations.FetchProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@CrossOrigin
public class RideDetailsController {

    private final UserDetailService userDetailService;

    public RideDetailsController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping
//    @ApiOperation(value = "Getting a Unique Ride number" , notes = "Customer is  sending all the details regarding location and price and getting unique ride number as response")
    public ResponseEntity<String> bookRide(@RequestBody RideDetailsRequestDto rideDetailsRequestDto){
        String rideNumber = userDetailService.generateRideNumber(rideDetailsRequestDto);
        return ResponseEntity.ok(rideNumber);
    }

//    @GetMapping("fetchRiders/{latitude}/{longitude}")
//    public ResponseEntity<RiderGraphResponseDto> fechRiders() {
//
//    }
}
