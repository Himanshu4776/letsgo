package com.example.letsgo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDetailsRequestDto {

    private double destinationLatitude;
    private double destinationLongitude;
    private double departureLatitude;
    private double departureLongitude;
    private float price;
    private String rideType;

    private long userId;

}
