package com.example.letsgo.Service;

import com.example.letsgo.Dto.RideDetailsRequestDto;
import com.example.letsgo.Dto.RiderGraph.RiderGraphResponseDto;

public interface RiderDetailService {

    String generateRideNumber(RideDetailsRequestDto rideDetailsRequestDto);

    RiderGraphResponseDto getAllRiders(double userLatitude, double userLongitude);

}
