package com.example.letsgo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDetailsResponseDto {
    private String id;

    private String Message;

    private int statusCode;

    private String rideNumber;
}
