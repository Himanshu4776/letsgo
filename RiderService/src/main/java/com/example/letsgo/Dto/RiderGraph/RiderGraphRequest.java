package com.example.letsgo.Dto.RiderGraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderGraphRequest {
    private double userLatitude;
    private double userLongitude;
}
